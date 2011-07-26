package de.banapple.eclipse.plugins.templates.resolver;

import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.corext.template.java.CompilationUnitContext;
import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateVariableResolver;

public class ArgumentsDebugStringResolver 
	extends TemplateVariableResolver
{

	@Override
	protected String resolve(TemplateContext context)
	{
		IJavaElement element= ((CompilationUnitContext) context).findEnclosingElement(IJavaElement.METHOD);
		if (element == null)
			return null;

		IMethod method= (IMethod) element;

		try {
			String[] arguments= method.getParameterNames();
			StringBuffer buffer= new StringBuffer();

			for (int i= 0; i < arguments.length; i++) {
				if (i > 0)
					buffer.append(", "); //$NON-NLS-1$
				buffer.append(arguments[i]).append("={}");
			}

			return buffer.toString();

		} catch (JavaModelException e) {
			return null;
		}
	}

}
