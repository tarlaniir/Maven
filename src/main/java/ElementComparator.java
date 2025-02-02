import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class ElementComparator {

    public static void compareElements(WebElement element1, WebElement element2) {

        Point loc1 = element1.getLocation();
        Point loc2 = element2.getLocation();


        if (loc1.getY() < loc2.getY()) {
            System.out.println("Element 1 находится выше, чем Element 2.");
        } else if (loc1.getY() > loc2.getY()) {
            System.out.println("Element 2 находится выше, чем Element 1.");
        } else {
            System.out.println("Оба элемента находятся на одной вертикали.");
        }


        if (loc1.getX() < loc2.getX()) {
            System.out.println("Element 1 находится левее, чем Element 2.");
        } else if (loc1.getX() > loc2.getX()) {
            System.out.println("Element 2 находится левее, чем Element 1.");
        } else {
            System.out.println("Оба элемента находятся на одной горизонтали.");
        }


        Dimension size1 = element1.getSize();
        Dimension size2 = element2.getSize();
        int area1 = size1.getWidth() * size1.getHeight();
        int area2 = size2.getWidth() * size2.getHeight();

        if (area1 > area2) {
            System.out.println("Element 1 занимает большую площадь (" + area1 + ") по сравнению с Element 2 (" + area2 + ").");
        } else if (area1 < area2) {
            System.out.println("Element 2 занимает большую площадь (" + area2 + ") по сравнению с Element 1 (" + area1 + ").");
        } else {
            System.out.println("Оба элемента занимают одинаковую площадь (" + area1 + ").");
        }
    }
}

