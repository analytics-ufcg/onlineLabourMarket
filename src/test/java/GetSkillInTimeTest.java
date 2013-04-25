import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class GetSkillInTimeTest {
	
	@BeforeClass
	public static void cleanTestDir(){
		// delete all .txt files in this directory
	}

	private String demandFileName = "demand.txt";
	private String rankingFileName = "ranking.txt";
	
	@Before
	public void buildInputFiles(){
		// create input files with txt extension:
		
		// demand file
		
//		122&|&0&|&https://www.elance.com/j/build-landing-page/34639249/"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&Graphic Design&|&0
//		123&|&0&|&https://www.elance.com/j/build-landing-page/34639249/"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&Graphic Design&|&0
//		123&|&0&|&https://www.elance.com/j/update-online-web-form/34639235/"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&MySQL;HTML&|&0
//		123&|&0&|&https://www.elance.com/j/banner-ads/34639228/"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&Animation;Graphic Design&|&0
//		123&|&0&|&https://www.elance.com/j/funeral-pre-planning-soft-ware/34639187/"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&MySQL;Palm&|&0
//		123&|&0&|&https://www.elance.com/j/ios-picture-video-sharing-app/34639251/"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&MySQL;iPhone&|&0
//		124&|&0&|&https://www.elance.com/j/ios-picture-video-sharing-app/34639251/"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&MySQL;iPhone&|&0
//		125&|&0&|&https://www.elance.com/j/ios-picture-video-sharing-app/34639251/"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&MySQL;iPhone&|&0

		// ranking file
		
//		MySQL;3
//		Graphic Design;2
//		HTML;1
//		Palm;1
//		iPhone;1
//		Animation;1

	}
	
	@After
	public void deleteAllFiles(){
		cleanTestDir();
	}

	@Test
	public void testFilterDataBySkill() throws IOException {
		GetSkillInTime.filterDataBySkill(demandFileName , "MySQL");
		Scanner inputScanner = new Scanner("MySQL.txt");
		assertEquals("123;0",inputScanner.nextLine());
		assertEquals("124;3",inputScanner.nextLine());
		assertEquals("125;1",inputScanner.nextLine());
		inputScanner.close();
	}

}
