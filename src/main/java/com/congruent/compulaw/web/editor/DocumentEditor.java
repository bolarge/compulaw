package com.congruent.compulaw.web.editor;

import java.beans.PropertyEditorSupport;

import com.congruent.compulaw.domain.Document;
import com.congruent.compulaw.service.DocumentService;

public class DocumentEditor extends PropertyEditorSupport{

		private DocumentService documentService;

		public DocumentEditor() {}

		public DocumentEditor(DocumentService documentService) {
			super();
			this.documentService = documentService;
		}
		
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			Long documentId = Long.parseLong(text);
			Document document = documentService.findById(documentId);
			setValue(document);
		}
}
