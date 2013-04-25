import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class GetSkillInTimeTest {
	
	@BeforeClass
	public static void cleanTestDir(){
		new File("*txt").delete();
	}

	private String demandFileName = "demand.txt";
	private String rankingFileName = "ranking.txt";
	
	@Before
	public void buildInputFiles() throws IOException{
		// create input files with txt extension:
		
		FileWriter fileWriter = new FileWriter(new File(demandFileName));

		fileWriter.write("1351114699&|&build landing page&|&https://www.elance.com/j/build-landing-page/34639249/ &|&Fixed Price&|&aeruimisrael-aeruimisrael,Israel&|&34639249profurl&|&15d, 0h&|& About $20&|& About $20&|&0&|&i need build landing page web bullding + seo expert to long time job who have big offer dont botter add higer price&|&Design & Multimedia&|&Graphic Design;Logo Design;Photoshop;Web Design&|&2\n");
		fileWriter.write("1351114699&|&Update Online Web Form&|&https://www.elance.com/j/update-online-web-form/34639235/ &|&Fixed Price&|&starryman-starryman,United States&|&34639235profurl&|&1d, 23h&|& Less than $500&|& Less than $500&|&0&|&Hello, We currently have an online web form that takes appointments. Currently when the customer submits the form, the form's data is sent to 5 different emails. Everything works well but we would like the form to now send a confirmation email of the appointment to the customer. Basically a reminder email. The confirmation/reminder email should provide: Customers contact info, appointment time/day, and general contact information to our store. Thank you for looking. Nicholas&|&IT & Programming&|&MySQL;HTML;PHP&|&4\n");
		fileWriter.write("1351114699&|&Banner Ads&|&https://www.elance.com/j/banner-ads/34639228/ &|&Fixed Price&|&Jockbrokers-Jockbrokers,United States&|&34639228profurl&|&1d, 23h&|& Less than $500&|& Less than $500&|&0&|&We need 3 banners created, all should be different and they are different sizes. 728x90 780x35 300x60 They need to be done by Friday 10/26/12 BEFORE 11pm est.. We can use movement, slideshow, and other things as long as the file size is below 40k each. No sound, but video can be used if muted. We prefer a slideshow type of banner to be able to give more information in such a small space but we are open to suggestions. While we prefer to do business with US-based companies we will be somewhat flexible on that based on previous work and price.&|&Design & Multimedia&|&Animation;Graphic Design;Banner Ads&|&2\n");
		fileWriter.write("1351114699&|&Funeral Pre-planning soft ware&|&https://www.elance.com/j/funeral-pre-planning-soft-ware/34639187/ &|&Fixed Price&|&J3ffG0u7d-J3ffG0u7d,United States&|&34639187profurl&|&15d, 0h&|& Not Sure&|& Not Sure&|&0&|&A drop-down list of services and merchandise items. Simply highlight what service and/or item you want- it totals them, calculate taxes and give a total. cutting and pasting pictures, logos a plus&|&IT & Programming&|&iPhone;Palm&|&0\n");
		fileWriter.write("1351114699&|&Modify Jigoshop Payment Gateway Plugin to Work With WooCommerce&|&https://www.elance.com/j/modify-jigoshop-payment-gateway-plugin-work-woocommerce/34639161/ &|&Hourly&|&digisavvy-digisavvy,United States&|&34639161profurl&|&15d, 0h&|& $15 - $20 / hr for 10 hrs&|& $15 - $20 / hr for 10 hrs&|&0&|&I have a need to modify an existing Jigoshop Gateway plugin so that it works with WooCommerce. The plugin is here currently:[obscured]/jigoshop-paypal-payflow-link-gateway/0.9/paypal_payflowlink.php Give me your best estimate for cost and turn around time. You should be able to demonstrate that this works and that i can configure it for our own accounts to ensure functionality, security and stability Good luck&|&IT & Programming&|&MySQL;PHP;WordPress&|&3\n");
		fileWriter.write("1351114699&|&Social Media Expert&|&https://www.elance.com/j/social-media-expert/34639144/ &|&Hourly&|&iamyeng-iamyeng*,Philippines&|&34639144profurl&|&15d, 0h&|& Less than $10 / hr for 9+ mos&|& Less than $10 / hr for 9+ mos&|&0&|&Social Media Expert Requirements: -MUST have video posting experience, optimize them, and post to YouTube. -Solo ads posting -Twitter conversation hacks -Tweet hacking -FB instant List Building -FB inbox blast -YouTube Traffic Strategies -Must be comfortable working in US Time zones -At least 1 mbps of internet speed or higher -With backup in case of internet interruption or power loss&|&Admin Support&|&Admin Assistant;Research;Computer&|&1\n");
		fileWriter.write("1351114699&|&Research Project&|&https://www.elance.com/j/research-project/34639139/ &|&Fixed Price&|&ShiqueDesign-ShiqueDesign,Australia&|&34639139profurl&|&15d, 0h&|& Not Sure&|& Not Sure&|&0&|&Our client requires the following research: The client wishes to obtain accurate data on the total global number of Antique and Collectable businesses currenty listed in online business directories in every country and territory worldwide. The following steps would be recommended: 1. Compile a list of current Sovereign States in the world. 2. Using a translation service, determine what online business directory (if any) is the most common/popular for each of these countries (ie. Yellow Pages in U.K). Compile this data. 3. Perform a search on each of these directories for Antiques and for Collectables in the relevent language to determine how many businesses are listed in these Categories in each country as a whole. Compile these numbers. Please provide quotes on the above project. Please note: if you are not capable or do not have the resources to perform every task in this research project please do not bid. Thank you.&|&Admin Support&|&Admin Assistant;Data Entry;Research&|&3\n");
		fileWriter.write("1351115263&|&iphone application&|&https://www.elance.com/j/iphone-application/34637170/ &|&Fixed Price&|&rashyedi-rashyedi,Israel&|&34637170profurl&|&14d, 23h&|& $5,000 - $10,000&|& $5,000 - $10,000&|&4&|&take your picture. drag and drop sunglasses onto your face. Interactive game that allows you discounts to branded sunglasses delivered to your door anywhere in the world via their website.&|&IT & Programming&|&iPhone;Java;Objective-C;iPad;Game Development&|&0\n");
		fileWriter.write("1351115263&|&Website Developer - Music Industry&|&https://www.elance.com/j/website-developer-music-industry/34637132/ &|&Fixed Price&|&stevemurray15-stevemurray15,United States&|&34637132profurl&|&2d, 22h&|& Not Sure&|& Not Sure&|&2&|&Looking for someone to build the front-end platform of a digital distribution music site. Initially, I am looking for the development of 3-5 web-pages that has the potential to be integrated with an ASP back-end system. Eventually would like to move this to a iOS & Android App.&|&IT & Programming&|&MySQL;HTML;PHP&|&0\n");
		fileWriter.write("1351115332&|&Redesign Wordpress Site&|&https://www.elance.com/j/redesign-wordpress-site/34639493/ &|&Fixed Price&|&ivito-ivito,Spain&|&34639493profurl&|&15d, 0h&|& Not Sure&|& Not Sure&|&0&|&Seeking programmer, web designer or a professional design templates for wordpress. I need to stop my template with all necessary plugings and nice design. Greetings.&|&IT & Programming&|&CSS;HTML;WordPress&|&0\n");
		fileWriter.write("1351115332&|&Financial Articles On Debt and Personal Finance&|&https://www.elance.com/j/financial-articles-on-debt-personal-finance/34639492/ &|&Fixed Price&|&vinfogroup-vinfogroup,United States&|&34639492profurl&|&15d, 0h&|& Less than $500&|& Less than $500&|&0&|&Financial content writer needed. We need a skilled writer for weekly articles about personal credit and finance. Focusing on getting out of debt. These articles should be SEO friendly. The articles will also be converted to video. They will need to be written with that in mind. An animated Character will read the article for weekly video post. Writer must have knowledge or good research skills on personal credit and debt related issues. Content needs to be accurate, and an easy read as well as interesting. This is a long term project that may expand into multiple articles each week for different websites. Right now we are looking for 2 articles a week with about 500 words each.&|&Writing & Translation&|&Financial Writing;Web Content;Article Writing;Content Writing&|&4\n");
		
		fileWriter.flush();
		fileWriter.close();

		fileWriter = new FileWriter(new File(rankingFileName));

		fileWriter.write("Bnjnj;2\n");
		fileWriter.write("HTML;2\n");
		
		fileWriter.flush();
		fileWriter.close();


	}
	
	@After
	public void deleteAllFiles(){
		cleanTestDir();
	}

	@Test
	public void testFilterDataBySkill() throws IOException {
		GetSkillInTime.filterDataBySkill(demandFileName , "HTML");
		Scanner inputScanner = new Scanner("HTML.txt");
		assertEquals("1351114699;1",inputScanner.nextLine());
		assertEquals("1351115263;1",inputScanner.nextLine());
		assertEquals("1351115332;1",inputScanner.nextLine());
		inputScanner.close();
	}

}
