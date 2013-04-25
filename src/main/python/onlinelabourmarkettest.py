#from GetSkillRanking import GetSkillRanking
import onlinelabourmarket
import unittest
import os
import shutil

class TestSequenceFunctions(unittest.TestCase):
    
    def setUp(self):
        self.inputName = "testInput.txt"
        inputFile = open(self.inputName,"w")
        inputFile.write("122&|&0&|&https://www.elance.com/j/build-landing-page/34639249/ &|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&Graphic Design&|&0\n")
        inputFile.write("123&|&0&|&https://www.elance.com/j/build-landing-page/34639249/ &|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&Graphic Design&|&0\n")
        inputFile.write("123&|&0&|&https://www.elance.com/j/update-online-web-form/34639235/ &|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&MySQL;HTML&|&0\n")
        inputFile.write("123&|&0&|&https://www.elance.com/j/banner-ads/34639228/ &|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&Animation;Graphic Design&|&0\n")
        inputFile.write("123&|&0&|&https://www.elance.com/j/funeral-pre-planning-soft-ware/34639187/ &|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&MySQL;Palm&|&0\n")
        inputFile.write("123&|&0&|&https://www.elance.com/j/ios-picture-video-sharing-app/34639251/ &|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&MySQL;iPhone&|&0\n")
        inputFile.write("124&|&0&|&https://www.elance.com/j/ios-picture-video-sharing-app/34639251/ &|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&MySQL;iPhone&|&0\n")
        inputFile.write("125&|&0&|&https://www.elance.com/j/ios-picture-video-sharing-app/34639251/ &|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&MySQL;iPhone&|&0\n")

        self.inputWrongName = "testWrongInput.txt"
        inputFileWrong = open(self.inputWrongName,"w")
        inputFileWrong.write("1351114699&|&iOS picture/video sharing app&|&https://www.elance.com/j/ios-picture-video-sharing-app/34639251/\" &|&Fixed Price&|&teamxs-teamxs,United States&|&34639251profurl&|&30d, 0h&|& $5,000 - $10,000&|& $5,000 - $10,000&|&0&|&We are developing an iOS app that is a combination of Instagram and Viddy. It is tailored for a niche industry and enables consumers to upload, comment and rate pictures/video. We are a team of San Francisco based product/graphic designers and need your coding expertise to round out this build. Deliverables From Us: We have completed the prototype/wireframes and written the PRD. We will also provide you with the final visual design files (PSD's) by December 10th. Deliverables From You: We need you to develop the app for iOS (iPhone 4,4s,5) based on our specifications and then help us configure it on Amazon's AWS. Requirements for this contract: ? Be able to write elegant, readable, and well-documented code. ? Development experience using ObjC, C++ and C; ability to work with Cocoa/UIKit frameworks and the iPhone SDK. ? Familiarity with OS-specific APIs and demonstrated experience launching production-ready code. ? Design Backend & DB schema with support from us ? Experience w...&|&IT & Programming&|&SMySQL;iPhone;Cocoa;Amazon Web Services;iOS&|&0\n")
        inputFileWrong.write("1351114699&|&build landing page&|&https://www.elance.com/j/build-landing-page/34639249/\" &|&Fixed Price&|&aeruimisrael-aeruimisrael,Israel&|&34639249profurl&|&15d, 0h&|& About $20&|& About $20&|&0&|&i need build landing page web bullding + seo expert to long time job who have big offer dont botter add higer price&|&Design & Multimedia&|&SGraphic Design;Logo Design;Photoshop;Web Design&|&2\n")
        inputFileWrong.write("1351114699&|&Update Online Web Form&|&https://www.elance.com/j/update-online-web-form/34639235/\" &|&Fixed Price&|&starryman-starryman,United States&|&34639235profurl&|&1d, 23h&|& Less than $500&|& Less than $500&|&0&|&Hello, We currently have an online web form that takes appointments. Currently when the customer submits the form, the form's data is sent to 5 different emails. Everything works well but we would like the form to now send a confirmation email of the appointment to the customer. Basically a reminder email. The confirmation/reminder email should provide: Customers contact info, appointment time/day, and general contact information to our store. Thank you for looking. Nicholas&|&IT & Programming&|&SMySQL;HTML;PHP&|&4\n")

        inputFile.close()
        inputFileWrong.close()
    
    def tearDown(self):
        os.remove(self.inputName)
        os.remove(self.inputWrongName)

    def testBuildRanking(self):
        ranking = onlinelabourmarket.buildRanking(self.inputName, 2, 12)
        expectedRanking = {"MySQL":3,"Graphic Design":2,"HTML":1,"Palm":1,"iPhone":1,"Animation":1}
        for item in expectedRanking:
            self.assertTrue(item in ranking)
            self.assertEquals(expectedRanking[item],ranking[item])

    #TOFIX
    def testFormatDemandFile(self):
        expectedOutput = list()
        expectedOutput.append("1351114699&|&iOS picture/video sharing app&|&https://www.elance.com/j/ios-picture-video-sharing-app/34639251/ &|&Fixed Price&|&teamxs-teamxs,United States&|&34639251profurl&|&30d, 0h&|& $5,000 - $10,000&|& $5,000 - $10,000&|&0&|&We are developing an iOS app that is a combination of Instagram and Viddy. It is tailored for a niche industry and enables consumers to upload, comment and rate pictures/video. We are a team of San Francisco based product/graphic designers and need your coding expertise to round out this build. Deliverables From Us: We have completed the prototype/wireframes and written the PRD. We will also provide you with the final visual design files (PSD's) by December 10th. Deliverables From You: We need you to develop the app for iOS (iPhone 4,4s,5) based on our specifications and then help us configure it on Amazon's AWS. Requirements for this contract: ? Be able to write elegant, readable, and well-documented code. ? Development experience using ObjC, C++ and C; ability to work with Cocoa/UIKit frameworks and the iPhone SDK. ? Familiarity with OS-specific APIs and demonstrated experience launching production-ready code. ? Design Backend & DB schema with support from us ? Experience w...&|&IT & Programming&|&MySQL;iPhone;Cocoa;Amazon Web Services;iOS&|&0")
        expectedOutput.append("1351114699&|&build landing page&|&https://www.elance.com/j/build-landing-page/34639249/ &|&Fixed Price&|&aeruimisrael-aeruimisrael,Israel&|&34639249profurl&|&15d, 0h&|& About $20&|& About $20&|&0&|&i need build landing page web bullding + seo expert to long time job who have big offer dont botter add higer price&|&Design & Multimedia&|&Graphic Design;Logo Design;Photoshop;Web Design&|&2")
        expectedOutput.append("1351114699&|&Update Online Web Form&|&https://www.elance.com/j/update-online-web-form/34639235/\" &|&Fixed Price&|&starryman-starryman,United States&|&34639235profurl&|&1d, 23h&|& Less than $500&|& Less than $500&|&0&|&Hello, We currently have an online web form that takes appointments. Currently when the customer submits the form, the form's data is sent to 5 different emails. Everything works well but we would like the form to now send a confirmation email of the appointment to the customer. Basically a reminder email. The confirmation/reminder email should provide: Customers contact info, appointment time/day, and general contact information to our store. Thank you for looking. Nicholas&|&IT & Programming&|&SMySQL;HTML;PHP&|&4")
        onlinelabourmarket.formatDemandFile(self.inputWrongName)
        f = open(self.inputWrongName)
        for i in range(len(f)):
            self.assertEqual(str(f[i]),expectedOutput[i])
        f.close()
        os.remove(self.f.name)
        

if __name__ == '__main__':
    unittest.main()
