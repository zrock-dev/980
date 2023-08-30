package com.fake_orgasm.generator.user_generator.combinatory_parts;

import com.fake_orgasm.generator.utils.FileReader;
import com.fake_orgasm.generator.utils.Notifiable;

public class Worker extends Piece implements Notifiable {

    public Worker(FileReader fileReader, Notifiable neighbor) {
        super(fileReader, neighbor);
    }

    public String next() {
        return current;
    }

    @Override
    public void startup() {
        iterator = items.iterator();
        current = iterator.next();
    }

    @Override
    public void doNotify() {
        if (!isEmpty()) {
            current = iterator.next();
        } else {
            iterator = items.iterator();
            current = iterator.next();
            neighbor.doNotify();
        }
    }
}
