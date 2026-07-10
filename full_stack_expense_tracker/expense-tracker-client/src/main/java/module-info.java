module expense.tracker.client {
    requires javafx.controls;
    requires com.google.gson;
    requires java.desktop;

    opens org.example.models to javafx.base;

    exports org.example;


}