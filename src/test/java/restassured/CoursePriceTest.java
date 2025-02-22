package restassured;

import data.Payload;
import io.restassured.path.json.JsonPath;

import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class CoursePriceTest {
    //TODO
    /*
        1. Print No of courses returned by API

        2.Print Purchase Amount

        3. Print Title of the first course

        4. Print All course titles and their respective Prices

        5. Print no of copies sold by RPA Course

        6. Verify if Sum of all Course prices matches with Purchase Amount
     */

    JsonPath js = new JsonPath(Payload.coursePriceJson());

    @Test
    public void numOfCoursesTest() {
       System.out.println(js.getInt("courses.size()"));
       assertEquals(js.getInt("courses.size()"),3);

    }

    @Test
    public void purchaseAmtTest() {
        System.out.println(js.getInt("dashboard.purchaseAmount"));
        assertEquals(js.getInt("dashboard.purchaseAmount"),910);

    }
    @Test
    public void titleOfTheFirstCourseTest() {
        System.out.println( js.get("courses[0].title").toString());
        assertEquals(js.get("courses[0].title").toString(), "Selenium Python");

    }
    @Test
    public void allCoursesAndPricesTest() {
        for(int i =0;i<js.getInt("courses.size()");i++) {
            System.out.println(js.get("courses["+i+"].title").toString());
            System.out.println(js.getInt("courses["+i+"].price"));
        }

    }
    @Test
    public void numOfCopiesSoldForRPATest() {
        for(int i =0;i<js.getInt("courses.size()");i++) {
            if(js.get("courses["+i+"].title").toString().equalsIgnoreCase("RPA")) {
                System.out.println(js.getInt("courses[" + i + "].price"));
                break;
            }
        }

    }
    @Test
    public void sumOfAllCoursesMatchingWithPurchaseAmtTest() {
        int sumTotal =0;
        int sumPerCourse=0;
        for(int i =0;i<js.getInt("courses.size()");i++) {
            sumPerCourse= js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies");
            System.out.println("sumPerCourse "+sumPerCourse);
            sumTotal+=sumPerCourse;
        }
        System.out.println("sumTotal "+sumTotal);
        assertEquals(sumTotal,js.getInt("dashboard.purchaseAmount"));

    }


}
