package page_objects;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class NavigationPageRplCommandName {



    //�������� ������� �� ������ ��������
    private SelenideElement elevened = $x("/html/body/div[9]/div[4]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]/a");

    public void getCommandToThePoint(){

        elevened.scrollIntoView(false);//����� �� ��������

        SelenideElement lastNameClubClick = $x("/html/body/div[9]/div[4]/div[2]/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]/div[1]");

        // �������� ����� ��� ��������
        String lastNameClub = elevened.getText();
        lastNameClubClick.shouldBe(Condition.exactText(lastNameClub));

        System.out.println(lastNameClub);
    }

}

