package com.example.android_homework1.contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ContactContent {
    public static final List<Contact> ITEMS = new ArrayList<Contact>();
    public static final Map<String, Contact> ITEM_MAP = new HashMap<String, Contact>();

    private static final int COUNT = 5;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createContact(i));
        }
    }

    public static void addItem(Contact item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Contact createContact(int position) {
        return new Contact(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        //for (int i = 0; i < position; i++) {
         //   builder.append("\nMore details information here.");
        //}
        return builder.toString();
    }
    public static void removeItem(int currentItemPosition) {
        String id = ITEMS.get(currentItemPosition).id;
        ITEMS.remove(currentItemPosition);
        ITEM_MAP.remove(id);
    }

    public static class Contact {
        public final String id;
        public final String nameSurname;
        public final String phoneNumber;
        public final String soundPath;
        public final String picPath;

        public Contact(String id, String nameSurname, String phoneNumber) {
            this.id = id;
            this.nameSurname = nameSurname;
            this.phoneNumber = phoneNumber;
            this.picPath = "";
            this.soundPath = "";
        }
        public Contact(String id, String nameSurname, String phoneNumber, String soundPath, String picPath) {
            this.id = id;
            this.nameSurname = nameSurname;
            this.phoneNumber = phoneNumber;
            this.soundPath = soundPath;
            this.picPath = picPath;

        }

        @Override
        public String toString() {
            return nameSurname;
        }
    }
}