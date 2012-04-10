package br.com.triadworks.issuetracker.controller.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.triadworks.issuetracker.dao.IssueDao;
import br.com.triadworks.issuetracker.model.Issue;

@FacesConverter(forClass=Issue.class)
public class IssueConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		
		if (value == null)
			return null;
		
		Long id = new Long(value);
		
		IssueDao dao = ctx.getApplication()
				.evaluateExpressionGet(ctx, "#{issueDao}", IssueDao.class);
		
		Issue issue = dao.carrega(id);
		return issue;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		
		if (value == null)
			return null;
		
		Issue issue = (Issue) value;
		return issue.getId().toString();
	}

}
