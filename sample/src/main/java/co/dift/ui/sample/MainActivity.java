package co.dift.ui.sample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

import co.dift.ui.SwipeToAction;


public class MainActivity extends Activity {

    RecyclerView recyclerView;
    BooksAdapter adapter;
    SwipeToAction swipeToAction;

    List<Book> books = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // facebook image library
        Fresco.initialize(this);

        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new BooksAdapter(this.books);
        recyclerView.setAdapter(adapter);

        swipeToAction = new SwipeToAction(recyclerView, new SwipeToAction.SwipeListener<Book>() {
            @Override
            public boolean swipeLeft(final Book itemData) {
                final int pos = removeBook(itemData);
                displaySnackbar(itemData.getTitle() + " removed", "Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addBook(pos, itemData);
                    }
                });
                return true;
            }

            @Override
            public boolean swipeRight(Book itemData) {
                displaySnackbar(itemData.getTitle() + " loved", null, null);
                return true;
            }

            @Override
            public void onClick(Book itemData) {
                displaySnackbar(itemData.getTitle() + " clicked", null, null);
            }

            @Override
            public void onLongClick(Book itemData) {
                displaySnackbar(itemData.getTitle() + " long clicked", null, null);
            }
        });


        populate();

        // use swipeLeft or swipeRight and the elem position to swipe by code
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToAction.swipeRight(2);
            }
        }, 3000);
    }

    private void populate() {
        this.books.add(new Book("Einstein: his life and universe", "Walter Isaacson", "http://static.bookstck.com/books/einstein-his-life-and-universe-400.jpg"));
        this.books.add(new Book("Zero to One: Notes on Startups, or How to Build the Future", "Peter Thiel, Blake Masters", "http://static.bookstck.com/books/zero-to-one-400.jpg"));
        this.books.add(new Book("Tesla: Inventor of the Electrical Age", "W. Bernard Carlson", "http://static.bookstck.com/books/tesla-inventor-of-the-electrical-age-400.jpg"));
        this.books.add(new Book("Orwell's Revenge: The \"1984\" Palimpsest", "Peter Huber", "http://static.bookstck.com/books/orwells-revenge-400.jpg"));
        this.books.add(new Book("How to Lie with Statistics", "Darrell Huff", "http://static.bookstck.com/books/how-to-lie-with-statistics-400.jpg"));
        this.books.add(new Book("Abundance: The Future Is Better Than You Think", "Peter H. Diamandis, Steven Kotler", "http://static.bookstck.com/books/abundance-400.jpg"));
        this.books.add(new Book("Where Good Ideas Come From", "Steven Johnson", "http://static.bookstck.com/books/where-good-ideas-come-from-400.jpg"));
        this.books.add(new Book("The Information: A History, A Theory, A Flood", "James Gleick", "http://static.bookstck.com/books/the-information-history-theory-flood-400.jpg"));
        this.books.add(new Book("Turing's Cathedral: The Origins of the Digital Universe", "George Dyson", "http://static.bookstck.com/books/turing-s-cathedral-400.jpg"));
    }

    private void displaySnackbar(String text, String actionName, View.OnClickListener action) {
        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                .setAction(actionName, action);

        View v = snack.getView();
        v.setBackgroundColor(getResources().getColor(R.color.secondary));
        ((TextView) v.findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
        ((TextView) v.findViewById(android.support.design.R.id.snackbar_action)).setTextColor(Color.BLACK);

        snack.show();
    }

    private int removeBook(Book book) {
        int pos = books.indexOf(book);
        books.remove(book);
        adapter.notifyItemRemoved(pos);
        return pos;
    }

    private void addBook(int pos, Book book) {
        books.add(pos, book);
        adapter.notifyItemInserted(pos);
    }
}
