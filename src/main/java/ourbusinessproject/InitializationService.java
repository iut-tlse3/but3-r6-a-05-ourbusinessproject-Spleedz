package ourbusinessproject;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InitializationService {

    @Autowired
    private EnterpriseProjectService enterpriseProjectService;

    private Enterprise e1;
    private Enterprise e2;
    private Project p1e1;
    private Project p2e1;
    private Project p1e2;

    /*
     * On constate que si on as une erreur dans la création d'un des projets ou entreprise, le transactional permet
     * d'annuler la création de tout les éléments.
     */
    @Transactional
    public void initProjects(){
        this.e1 = this.enterpriseProjectService.newEnterprise("~Enterprise 1", "First Enterprise", "First Contact", "firstcontact@email.com");
        this.e2 = this.enterpriseProjectService.newEnterprise("~Enterprise 2", "Second Enterprise", "Second Contact", "secondcontact@email.com");

        this.p1e1 = this.enterpriseProjectService.newProject("~Project 1 Enterprise 1", "First Project Enterprise 1", this.e1);
        this.p2e1 = this.enterpriseProjectService.newProject("~Project 2 Enterprise 1", "Second Project Enterprise 1", this.e1);
        this.p1e2 = this.enterpriseProjectService.newProject("~Project 1 Enterprise 2", "First Project Enterprise 2", this.e2);
    }

    public Project getProject1E1() {
        return p1e1;
    }

    public Project getProject1E2() {
        return p1e2;
    }

    public Project getProject2E1() {
        return p2e1;
    }

    public Enterprise getEnterprise1() {
        return e1;
    }

    public Enterprise getEnterprise2() {
        return e2;
    }
}
