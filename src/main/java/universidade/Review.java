package universidade;

import java.time.LocalDate;

public class Review {
    private Student author;
    private Property property;
    private double rating;
    private String comment;
    private LocalDate publicationDate;
    private boolean isVisible;

    public Review(Student author, Property property, double rating, String comment) {
        this.author = author;
        this.property = property;
        this.rating = rating;
        this.comment = comment;
        this.publicationDate = LocalDate.now();
        this.isVisible = false;
    }

    public void publishReview() {
        this.isVisible = true;
    }

    public double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public boolean isVisible() {
        return isVisible;
    }
}
