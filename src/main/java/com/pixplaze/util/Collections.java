package com.pixplaze.util;

import java.util.*;

public class Collections {
    public static <T> T pop(T desired, Collection<T> collection) throws NoSuchElementException {
        for (var iter = collection.iterator(); iter.hasNext();) {
            var item = iter.next();
            if (item.equals(desired)) {
                iter.remove(); return item;
            }
        } throw new NoSuchElementException("Item not found!");
    }
}
