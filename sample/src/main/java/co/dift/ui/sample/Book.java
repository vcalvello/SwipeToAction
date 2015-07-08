package co.dift.ui.sample;

public class Book {
    private String title;
    private String author;
    private String imageUrl;

    public Book(String title, String author, String imageUrl) {
        this.title = title;
        this.author = author;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
