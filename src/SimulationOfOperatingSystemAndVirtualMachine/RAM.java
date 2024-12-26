package SimulationOfOperatingSystemAndVirtualMachine;

import javax.swing.*;
import java.util.ArrayList;

public class RAM {
    private final int frameCount;
    private final ArrayList<Frame> frames;

    public RAM(int frameCount){
        this.frameCount = frameCount;
        this.frames = new ArrayList<>(frameCount);

        for (int i = 0; i < frameCount; i++){
            frames.add(new Frame(i));
        }
    }

    public int getFrameCount(){
        return frameCount;
    }

    public boolean isAPageInRAM(int pageNumber){
        for (Frame frame: frames){
            if (frame.getPage() != null && frame.getPage().getPageNumber() == pageNumber){
                System.out.println("A page was found on RAM");
                return true;
            }
        }
        return false;
    }

    public Frame getFrameForThePage(int pageNumber){
        for (Frame frame: frames){
            if (frame.getPage() != null && frame.getPage().getPageNumber() == pageNumber){
                System.out.println("Frame isn't empty and it contains the asked page!");
                return frame;
            }
        }
        System.out.println("There's not a single matching frame!");
        return null;
    }

    public Frame getEmptyFrame(){
        for (Frame frame : frames){
            if (frame.getPage() == null){
                System.out.println("Frame doesn't contain a page! :) ");
                return frame;
            }
        }
        System.out.println("There's no empty frame! :( ");
        return null;

    }

    public Frame getLeastRecentlyUsedFrame(){
        Frame lruFrame = null;
        long oldestTime = Long.MAX_VALUE;

        for (Frame frame : frames){
            if (frame.getPage() != null){
                long lastUsedTime = frame.getPage().getLastUsageTime();
                if (lastUsedTime < oldestTime){
                    oldestTime = lastUsedTime;
                    lruFrame = frame;
                }
            }
        }
        return lruFrame;
    }

    public void loadPageIntoFrame(Page page){
        Frame frame = getEmptyFrame();
        if (frame == null){
            System.out.println("There's no empty frames, we're gonna evict the least recently used page");
            frame = getLeastRecentlyUsedFrame();
            System.out.println("We're evicting the least recently used page! " + frame.getPage().getPageNumber());

        }
        frame.setPage(page);
        System.out.println("Page " + page + " is loaded onto the frame " + frame.getFrameId());
    }

    public void printingFrames(){
        for (Frame frame: frames){
            if (frame.getPage() == null){
                System.out.println("Frame " + frame.getFrameId() + " is EMPTY!");
            }else {
                System.out.println("Frame " + frame.getFrameId() + " : " + frame.getPage());
            }
        }
    }



}
