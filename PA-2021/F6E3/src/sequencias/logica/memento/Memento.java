package sequencias.logica.memento;

import java.io.*;

public class Memento { // Memento genérico que dá para tudo quando se recorre a serialização ...
    private byte[] snapshot = null;  // para marcar a posição

    public Memento(Object obj) throws IOException {
        ByteArrayOutputStream baos;
        ObjectOutputStream oos = null;

        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            snapshot = baos.toByteArray();
        }finally {
            if(oos!=null){
                oos.close();
            }
        }
    }

    public Object getSnapshot() throws IOException, ClassNotFoundException {
        if (snapshot == null)
            return null;

        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(snapshot))) {
            return ois.readObject();
        }
    }
}
