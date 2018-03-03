package com.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class BreakPointHelper {
	public BreakPointHelper()
	{
		
	}
	public BreakPointHelper(String xmlpath)
	{		
		this.xmlPath=xmlpath;
	}
	private String rootName="configuration";
	private String xmlPath=CommonHelper.GetCurrentPath()+"/config/"; //"D:\\";

	private DocumentBuilderFactory GetDocumentBuilder() {
		DocumentBuilderFactory docservice = DocumentBuilderFactory.newInstance();
		docservice.setIgnoringElementContentWhitespace(true);
		return docservice;
	}

	private void SaveDocument(Document doc, String FullPath) {
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer former = factory.newTransformer();
			former.transform(new DOMSource(doc), new StreamResult(new File(FullPath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param FileName
	 * @param NodeName
	 * @param AttrName
	 * @return
	 */
	public String GetConfig(String FileName, String NodeName, String AttrName) {
		String result = "0";
		try {
			String FullPath = xmlPath + FileName+".xml";
			File file = new File(FullPath);
			if (file.exists()) {
				DocumentBuilder db = GetDocumentBuilder().newDocumentBuilder();
				Document doc = db.parse(FullPath);

				Element rootElement = doc.getDocumentElement();
				if (rootElement != null && "configuration".equals(rootElement.getTagName())) {
					NodeList nodelist = rootElement.getChildNodes();
					if (nodelist != null && nodelist.getLength() > 0) {

						for (int i = 0; i < nodelist.getLength(); i++) {
							Node node = nodelist.item(i);
							if (node.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) node;
								String ename = eElement.getNodeName();
								String evalue = eElement.getAttribute(AttrName);
								if (eElement.getNodeName() == NodeName && evalue != null) {
									result = evalue;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @param FileName
	 * @param NodeName
	 * @param AttrName
	 * @param AttrValue
	 * @return
	 */
	public String SaveConfig(String FileName, String NodeName, String AttrName, String AttrValue) {
		String result = "0";
		try {
			String FullPath = xmlPath + FileName+".xml";
			boolean ischange = false;
			boolean isfund = false;
			File file = new File(FullPath);
			Document doc = null;
			if (file.exists()) {
				DocumentBuilder db = GetDocumentBuilder().newDocumentBuilder();
				doc = db.parse(FullPath);
			} else {
				doc = GetDocTemplate();
			}
			Element rootElement = doc.getDocumentElement();
			if (rootElement != null &&rootName.equals(rootElement.getTagName())) {
				NodeList nodelist = rootElement.getChildNodes();
				if (nodelist != null && nodelist.getLength() > 0) {
					for (int i = 0; i < nodelist.getLength(); i++) {
						Node node = nodelist.item(i);
						if (node.getNodeType() == Node.ELEMENT_NODE) {
							Element eElement = (Element) node;
							if (eElement.getNodeName() == NodeName) {
								eElement.setAttribute(AttrName, AttrValue);
								isfund = true;
								ischange = true;
								break;
							}
						}
					}
				}
			}
			if (!isfund) {
				Element tempe = doc.createElement(NodeName);
				tempe.setAttribute(AttrName, AttrValue);
				rootElement.appendChild(tempe);

				ischange = true;
			}
			if (ischange) {

				SaveDocument(doc, FullPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public Document GetDocTemplate() {
		Document doc = null;
		DocumentBuilder domBuilder;
		try {
			DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
			domBuilder = domfac.newDocumentBuilder();
			doc = domBuilder.parse(GetInputStreamTemplate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}

	public InputStream GetInputStreamTemplate() {
		String content = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><configuration></configuration>";
		InputStream inputstream = null;
		try {
			inputstream = new ByteArrayInputStream(content.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return inputstream;
	}
}