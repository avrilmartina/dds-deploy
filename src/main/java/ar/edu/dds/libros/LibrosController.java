package ar.edu.dds.libros;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import io.javalin.http.Context;

public class LibrosController {

	private EntityManagerFactory entityManagerFactory;

	public LibrosController(EntityManagerFactory entityManagerFactory) {
		super();
		this.entityManagerFactory = entityManagerFactory;
	}

	public  void listLibros(Context ctx) {
		EntityManager em = entityManagerFactory.createEntityManager();
		
		RepoLibros repo = new RepoLibros(em);
		
		ctx.json(repo.findAll());
		
		em.close();
	}

	public  void addLibro(Context ctx) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
	    RepoLibros repo = new RepoLibros(em);
	    Libro libro = ctx.bodyAsClass(Libro.class);
	    repo.save(libro);
	    ctx.status(201);
	    em.getTransaction().commit();
	    em.close();
	}

}
