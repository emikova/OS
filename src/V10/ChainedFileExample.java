package V10;

class Block{
    private static final int DEFAULT_BLOCK_SIZE = 4;
    private String content;
    private int address;
    private Block next;

    public Block(int address){
        this.address = address;
        this.content = "";
        this.next = null;
    }

    public int getAddress(){
        return address;
    }

    public String getContent() {
        return content;
    }

    public Block getNext(){
        return next;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setNext(Block next){
        this.next = next;
    }

    public static int getDefaultBlockSize(){
        return DEFAULT_BLOCK_SIZE;
    }
}

class FileC{
    private String name;
    private Block head;

    public FileC(String name){
        this.name = name ;
        this.head = null;
    }

    public String getName() {
        return name;
    }

    public void writeContent(String content){
        int contentLength = content.length();
        int blockSize = Block.getDefaultBlockSize();
        int blockNeeded = (int)Math.ceil((double) contentLength/blockSize);

        if (blockNeeded > 0){
            this.head = new Block(0);
        }
        Block current = this.head;

        for (int i = 0; i < blockNeeded; i++){
            int start = i * blockSize;
            int end = Math.min(start + blockSize, contentLength);

            String blockContent = content.substring(start,end);
            current.setContent(blockContent);

            if (i < blockNeeded - 1){
                current.setNext(new Block(i + 1));
            }
            current = current.getNext();
        }

    }

    public String readContent(){
        StringBuilder  contentBuilder = new StringBuilder();
        Block current = head;
        while (current!= null){
            contentBuilder.append(current.getContent());
            current = current.getNext();

        }
        return contentBuilder.toString();
    }
}
public class ChainedFileExample {
    public static void main(String[] args) {
        FileC fileC = new FileC("example.txt");

        fileC.writeContent("This is a chained file example! :) ");
        String content = fileC.readContent();
        System.out.println("File content : " + content);
    }
}
