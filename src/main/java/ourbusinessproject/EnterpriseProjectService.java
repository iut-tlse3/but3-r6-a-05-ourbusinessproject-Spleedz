package ourbusinessproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseProjectService {

    @PersistenceContext
    private EntityManager entityManager;

    public EnterpriseProjectService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public Project newProject(String aTitle, String aDescription, Enterprise enterprise) {
        Project newProject = new Project();
        newProject.setTitle(aTitle);
        newProject.setDescription(aDescription);
        newProject.setEnterprise(enterprise);
        this.entityManager.persist(newProject);
        this.entityManager.flush();
        enterprise.addProject(newProject);
        return newProject;
    }

    public Enterprise newEnterprise(String aName, String aDescription, String aContactName, String mail) {
        Enterprise newEnterprise = new Enterprise();
        newEnterprise.setName(aName);
        newEnterprise.setDescription(aDescription);
        newEnterprise.setContactName(aContactName);
        newEnterprise.setContactEmail(mail);
        this.entityManager.persist(newEnterprise);
        this.entityManager.flush();
        return newEnterprise;
    }

    public Project findProjectById(Long anId) {
        return entityManager.find(Project.class, anId);
    }

    public Enterprise findEnterpriseById(Long anId) {
        return entityManager.find(Enterprise.class, anId);
    }

    public List<Project> findAllProjects() {
        String query = "select p FROM Project p order by p.title";
        TypedQuery<Project> queryObj = entityManager.createQuery(query,Project.class);
        return queryObj.getResultList();
    }
}
