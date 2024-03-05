package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement sava method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override // db수정이 없어서 트랜잭션 어노테이션을 안씀
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override // db수정이 없어서 트랜잭션 어노테이션을 안씀
    public List<Student> findAll() {

        // create query                                         // 여기서 jpql 조건을 붙일 수 있다. ex)findByLastName()
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student",Student.class);

        // return query results
        return theQuery.getResultList();
    }

    @Override // db수정이 없어서 트랜잭션 어노테이션을 안씀
    public List<Student> findByLastName(String theLastName) {

        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery(
                                                "FROM Student WHERE lastName=:theData", Student.class);

        // set query parameters
        theQuery.setParameter("theData",theLastName);

        // return query results
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);

    }

    @Override
    @Transactional
    public void delete(Integer id) {

        // retrieve the student
        Student theStudent = entityManager.find(Student.class,id);

        // delete the student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {

        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }
}
