package com.cncoderx.game.magictower.data;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by admin on 2017/5/19.
 */
public class ChatMessage extends ILoader {
    private IntMap<Array<Message>> mMessages = new IntMap<Array<Message>>();

    public ChatMessage(FileHandle file) {
        super(file);
        BufferedReader reader = new BufferedReader(file.reader());
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.trim().length() == 0) {
                    continue;
                }
                if (line.trim().charAt(0) == ';') {
                    continue;
                }
                String[] array = line.split("\\|");
                if (array.length >= 3) {
                    Message message = new Message();
                    int id = Integer.parseInt(array[0]);
                    message.id = id;
                    message.drawable = array[1];
                    message.content = array[2];

                    Array<Message> messages = mMessages.get(id);
                    if (messages == null) {
                        messages = new Array<Message>();
                        messages.add(message);
                        mMessages.put(id, messages);
                    } else {
                        messages.add(message);
                    }
                }
            }
        } catch (IOException e) {

        } finally {
            StreamUtils.closeQuietly(reader);
        }
    }

    public Array<Message> getMessageArray(int id) {
        return mMessages.get(id);
    }

    public class Message {
        public int id;
        public String drawable;
        public String content;
    }
}
