package com.molcon.mclabs.jatsutilitysuite.validation;

import java.io.File;
import java.io.IOException;

public class ValidationSampleUsage {
		public static void main(String[] args) throws Exception {
			ClassLoader classLoader = ValidationSampleUsage.class.getClassLoader();
			File dtdfile = new File(classLoader.getResource("DTD/JATS-Archiving-1-2-MathML2-DTD/JATS-archivearticle1.dtd").getFile());
			
			DTDValidation validator = new DTDValidation(dtdfile.getAbsolutePath());
			validator.validate("EMS85291.xml");
		}

}
