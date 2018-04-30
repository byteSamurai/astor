package fr.inria.astor.approaches.zm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.martiansoftware.jsap.JSAPException;

import fr.inria.astor.core.entities.ModificationPoint;
import fr.inria.astor.core.entities.ProgramVariant;
import fr.inria.astor.core.entities.SuspiciousModificationPoint;
import fr.inria.astor.core.entities.VariantValidationResult;
import fr.inria.astor.core.manipulation.MutationSupporter;
import fr.inria.astor.core.setup.ConfigurationProperties;
import fr.inria.astor.core.setup.ProjectRepairFacade;
import fr.inria.astor.core.solutionsearch.AstorCoreEngine;
import fr.inria.main.evolution.PlugInVisitor;

/**
 * Engine for Zimin approach which validates variants stored on Files.
 * 
 * @author Matias Martinez
 *
 */
public class ZmEngine extends AstorCoreEngine {

	public ZmEngine(MutationSupporter mutatorExecutor, ProjectRepairFacade projFacade) throws JSAPException {
		super(mutatorExecutor, projFacade);
		ConfigurationProperties.setProperty("skipfitnessinitialpopulation", "true");
		this.pluginLoaded = new PlugInVisitor() {

			@Override
			protected void loadValidator(AstorCoreEngine approach) throws Exception {
				FileProcessValidator fileValidator = new FileProcessValidator();
				approach.setProgramValidator(fileValidator);
			}

		};
	}

	public List<SuspiciousModificationPoint> getSuspicious() {
		List<SuspiciousModificationPoint> ls = new ArrayList<>();
		ProgramVariant pv = this.getVariants().get(0);
		for (ModificationPoint modificationPoint : pv.getModificationPoints()) {
			if (modificationPoint instanceof SuspiciousModificationPoint) {
				ls.add((SuspiciousModificationPoint) modificationPoint);
			}

		}
		return ls;
	}

	public VariantValidationResult validate(File modifiedFile) {
		VariantValidationResult result = null;

		return result;
	}

}
