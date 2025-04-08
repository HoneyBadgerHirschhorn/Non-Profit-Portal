package org.example.aletheiacares;

public class HelpRequestInterface {

    public interface HelpRequestView {
        String getTitle();
        String getFirstName();
        String getLastName();
        String getCategory();
        String getContent();
    }

}
