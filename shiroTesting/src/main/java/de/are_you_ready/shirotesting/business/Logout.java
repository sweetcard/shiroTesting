package de.are_you_ready.shirotesting.business;

import org.apache.shiro.SecurityUtils;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;

/**
 * Created by sebastianbasner on 03.12.16.
 */
@Named
@RequestScoped //automatic CDI injection when requested
public class Logout {

    /**
     * Shiro logout for the current user
     */
    public void submit() throws IOException {
        if (SecurityUtils.getSubject().hasRole("root")) {
            final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            SecurityUtils.getSubject().logout();
            externalContext.invalidateSession();  // cleanup user related session state
            externalContext.redirect("login.xhtml?faces-redirect=true");
        }
    }
}
