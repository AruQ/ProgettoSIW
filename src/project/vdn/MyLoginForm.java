package project.vdn;

import com.ejt.vaadin.loginform.LoginForm;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MyLoginForm extends LoginForm
{
    private static final long serialVersionUID = 1L;

    private final Mediator mediator;

    public MyLoginForm(final Mediator mediator)
    {
        this.mediator = mediator;
    }

    @Override
    protected Component createContent(final TextField userNameField, final PasswordField passwordField, final Button loginButton)
    {
        final VerticalLayout loginForm = new VerticalLayout();
        //        loginForm.setMargin(true);
        loginForm.setSpacing(true);

        loginForm.addStyleName("my_login_form");

        final HorizontalLayout headerLoginForm = createHeaderLoginPanel();

        final Label label = new Label("Login into...", ContentMode.HTML);
        headerLoginForm.addComponent(label);

        final VerticalLayout loginPanel = createLoginPanel(userNameField, passwordField, loginButton);
        loginForm.addComponent(headerLoginForm);
        loginForm.addComponent(loginPanel);
        loginForm.setExpandRatio(loginPanel, -1.0f);

        //        simpleLoginForm.addStyleName("my_simple_login_form");

        return loginForm;
    }

    protected HorizontalLayout createHeaderLoginPanel()
    {
        final HorizontalLayout headerLoginForm = new HorizontalLayout();
        headerLoginForm.setSizeFull();
        //        headerLoginForm.setSpacing(true);
        headerLoginForm.addStyleName("my_login_header_form");
        return headerLoginForm;
    }

    protected VerticalLayout createLoginPanel(final TextField userNameField, final PasswordField passwordField,
            final Button loginButton)
    {
        final VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(new MarginInfo(false, true, true, true));

        layout.addComponent(userNameField);
        layout.addComponent(passwordField);
        layout.addComponent(loginButton);
        layout.setComponentAlignment(loginButton, Alignment.BOTTOM_LEFT);
        return layout;
    }

    @Override
    protected void login(final String username, final String password)
    {
        System.err.println("Logged in with user name " + username + " and password of length " + password.length());
        mediator.tryLogin(username, password);
    }
}
