package jxd.bxb.test.All.EntityUtils;

import java.util.List;

/**
 * @author BXBstart
 * @create 2022-06-26 10:07
 */
public class Path {
    private String pathName;
    private Path child;

    public Path(String pathName, Path child) {
        this.pathName = pathName;
        this.child = child;
    }

    public Path(String pathName) {
        this.pathName = pathName;
    }

    public Path() {
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public Path getChild() {
        return child;
    }

    public void setChild(Path child) {
        this.child = child;
    }
}
