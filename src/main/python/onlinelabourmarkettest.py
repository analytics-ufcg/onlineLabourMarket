#from GetSkillRanking import GetSkillRanking
import onlinelabourmarket
import unittest
import os

class TestSequenceFunctions(unittest.TestCase):
    
    def setUp(self):
        self.inputName = "testInput.txt"
        inputFile = open(self.inputName,"w")
        inputFile.write("122&|&0&|&https://www.elance.com/j/build-landing-page/34639249/\"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&Graphic Design&|&0\n")
        inputFile.write("123&|&0&|&https://www.elance.com/j/build-landing-page/34639249/\"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&Graphic Design&|&0\n")
        inputFile.write("123&|&0&|&https://www.elance.com/j/update-online-web-form/34639235/\"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&MySQL;HTML&|&0\n")
        inputFile.write("123&|&0&|&https://www.elance.com/j/banner-ads/34639228/\"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&Animation;Graphic Design&|&0\n")
        inputFile.write("123&|&0&|&https://www.elance.com/j/funeral-pre-planning-soft-ware/34639187/\"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&MySQL;Palm&|&0\n")
        inputFile.write("123&|&0&|&https://www.elance.com/j/ios-picture-video-sharing-app/34639251/\"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&MySQL;iPhone&|&0\n")
        inputFile.write("124&|&0&|&https://www.elance.com/j/ios-picture-video-sharing-app/34639251/\"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&MySQL;iPhone&|&0\n")
        inputFile.write("125&|&0&|&https://www.elance.com/j/ios-picture-video-sharing-app/34639251/\"&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&0&|&MySQL;iPhone&|&0\n")

        inputFile.close()
    
    def tearDown(self):
        os.remove(self.inputName)

    def test_contFrequencia(self):
        print self.inputName
        ranking = onlinelabourmarket.buildRanking(self.inputName, 2, 12)
        expectedRanking = {"MySQL":3,"Graphic Design":2,"HTML":1,"Palm":1,"iPhone":1,"Animation":1}
        for item in expectedRanking:
            self.assertTrue(item in ranking)
            self.assertEquals(expectedRanking[item],ranking[item])

if __name__ == '__main__':
    unittest.main()
