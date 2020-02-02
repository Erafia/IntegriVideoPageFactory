package tests;

import models.Component;
import models.ProjectFile;
import models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProjectsPage;

import java.util.ArrayList;
import java.util.Arrays;

import static org.testng.Assert.assertEquals;

public class ProjectTest extends BaseTest {
    LoginPage loginpage;
    ProjectsPage projectsPage;
    User user = new User("verySecure", "integri_Erafia@mailinator.com");
    ProjectFile project = new ProjectFile("ProjectName", "ProjectDescription", new ArrayList<String>(Arrays.asList("www.leningrad.ru", "www.tochkaru.ru")));
    ProjectFile projectEdit = new ProjectFile("UpdProjectName", "UpdProjectDescription", new ArrayList<String>(Arrays.asList("www.leningrad.ru")));
    Component component = new Component("Multiparty Video", "WunschPunsch");
    Component component2 = new Component("Single Video", "EditedComponent");


    @BeforeMethod
    public void openProjectsPage() {
        loginpage = new LoginPage(driver);
        loginpage.openPage();
        projectsPage = loginpage.login(user);
    }

    @Test
    public void createProject() {
        int initialProjectCount = projectsPage.getProjectsCount();
        int finalProjectCount = projectsPage.createProject(project)
                .getProjectsCount();
        assertEquals(finalProjectCount, initialProjectCount + 1, "Количество проектов не увеличилось после добавления еще одного");
    }

    @Test
    public void editProject() {
        projectsPage.goToProjectPage(1)
                    .clickEditProjectButton()
                    .editProject(projectEdit);
        projectsPage.goToProjectPage(1)
                    .checkProjectFieldsEdited(projectEdit);
    }

    @Test
    public void createComponent() {
        projectsPage.goToProjectPage(1);
        int initialComponentCount = projectsPage.getComponentsCount();
        int finalComponentCount = projectsPage.clickAddComponentButton()
                .createComponent(component)
                .getComponentsCount();
       assertEquals(finalComponentCount,initialComponentCount + 1, "Количество компонентов не увеличилось после добавления еще одного");
    }

    @Test
    public void editComponent() {
        projectsPage.goToProjectPage(1)
                    .goToComponent(1)
                    .editComponent(component2);
        projectsPage.goToComponent(1).checkComponentNameEdited(component2);
    }
}
