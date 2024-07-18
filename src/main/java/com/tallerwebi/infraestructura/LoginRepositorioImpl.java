package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.login.LoginRepositorio;
import com.tallerwebi.dominio.usuario.Usuario;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class LoginRepositorioImpl implements LoginRepositorio {
    private final SessionFactory sessionFactory;
    public LoginRepositorioImpl(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    @Override

    public Usuario buscarUsuarioPorUsernameYPassword(String username, String password) {
        Boolean eliminado = false;
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("nombreUsuario", username))
                .add(Restrictions.eq("password", password))
                .add(Restrictions.eq("eliminado", eliminado))
                .uniqueResult();
    }
}
