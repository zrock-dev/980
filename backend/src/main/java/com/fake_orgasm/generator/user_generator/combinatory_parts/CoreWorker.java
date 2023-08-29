package com.fake_orgasm.generator.user_generator.combinatory_parts;

import com.fake_orgasm.generator.utils.FileReader;
import com.fake_orgasm.generator.utils.Notifiable;

public class CoreWorker extends Piece {

    public CoreWorker(FileReader fileReader, Notifiable neighbor) {
        super(fileReader, neighbor);
    }

    public String next() {
        if (isEmpty()) {
            iterator = items.iterator();
            neighbor.doNotify();
        }

        return iterator.next();
    }

    @Override
    public void startup() {
        iterator = items.iterator();
    }
}
