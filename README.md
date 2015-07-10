SwipeToAction
================
[![Download](https://api.bintray.com/packages/diftco/maven/swipetoaction/images/download.svg) ](https://bintray.com/diftco/maven/swipetoaction/_latestVersion)


An easy way to add a simple 'swipe-and-do-something' behavior to your `RecyclerView` items.
Just like in Gmail or Inbox apps.

![SwipeToAction Sample](https://raw.githubusercontent.com/diftco/SwipeToAction/master/screenshots/swipetoaction.gif)

### Integration
The lib is available on Maven Central, you can find it with [Gradle, please](http://gradleplease.appspot.com/#swipetoaction)

```
dependencies {
    compile 'co.dift.ui.swipetoaction:library:1.1'
}

```

### Usage

Check a demo project in the `sample/` folder.

1. The view you'll use for the items should have at least 3 children:
 - the one at the front (the last one or anyone with the `tag=front`)
 - the one to reveal when you swipe to left (before the front view or anyone with `tag=reveal-left`)
 - the one to reveal when you swipe to right (before the reveal-left view or anyone with `tag=reveal-right`)

    ```xml
     <!-- this view reveals when swipe right -->
     <RelativeLayout
        android:tag="reveal-right"
        android:background="@color/accent"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentLeft="true"
            android:layout_centerVertical="true"
            android:layout_marginLeft="20dp"
            android:tint="@color/icons"
            android:src="@mipmap/ic_favorite_black_24dp"/>
     </RelativeLayout>
    
     <!-- this view reveals when swipe left -->
     <RelativeLayout
        android:tag="reveal-left"
        android:background="@color/primary"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <ImageView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_alignParentRight="true"
            android:layout_centerVertical="true"
            android:layout_marginRight="20dp"
            android:tint="@color/icons"
            android:src="@mipmap/ic_delete_black_24dp"/>
     </RelativeLayout>
    
     <!-- this is the item front view -->
     <RelativeLayout
        android:tag="front"
        android:background="@color/item_background"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="@dimen/item_padding">
    
        <TextView
            android:id="@+id/title"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_toRightOf="@+id/image"
            android:singleLine="true"
            android:textSize="16dp"
            android:textStyle="bold" />
    
     </RelativeLayout>
    ```

2. In the adapter for your `RecyclerView` create a ViewHolder that extends SwipeToAction.ViewHolder<T>.
For instance, in the sample project I have a list of books and my items are instances of a Book class, therefore, the ViewHolder inside the adapter looks like this:

    ```java
       public class BookViewHolder extends SwipeToAction.ViewHolder<Book> {
         ...
         public BookViewHolder(View v) {
           super(v);
           ...
         }
       }
    ```
    Also, in the `onBindViewHolder` method remember to set the item data to the view holder instance
    
    ```java
      @Override
      public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Book item = items.get(position);
        BookViewHolder vh = (BookViewHolder) holder;
        vh.data = item;
    
        ...
    
      }
    ```

3. Set the `RecyclerView` as usual to create a vertical list, set the previous adapter and instantiate SwipeToAction
providing the recyclerView instance and a swipe listener `SwipeToAction.SwipeListener<T>`

    ```java
      recyclerView = (RecyclerView) findViewById(R.id.recycler);
      LinearLayoutManager layoutManager = new LinearLayoutManager(this);
      recyclerView.setLayoutManager(layoutManager);
      recyclerView.setHasFixedSize(true);
    
      adapter = new BooksAdapter(this.books);
      recyclerView.setAdapter(adapter);
    
      swipeToAction = new SwipeToAction(recyclerView, new SwipeToAction.SwipeListener<Book>() {
        @Override
        public boolean swipeLeft(final Book itemData) {
            //do something
            return true; //true will move the front view to its starting position
        }
    
        @Override
        public boolean swipeRight(Book itemData) {
            //do something
            return true;
        }
    
        @Override
        public void onClick(Book itemData) {
            //do something
        }
    
        @Override
        public void onLongClick(Book itemData) {
            //do something
        }
      });
    ```

# License

    Copyright (C) 2015 Dift.co

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
