package io.github.teuton.jteuton.model;

import java.net.URL;
import java.util.Map;

import io.github.teuton.jteuton.utils.URLUtils;

public class Repo {

	private String name;
	private String description;
	private String type;
	private URL url;
	private boolean enable = true;
	private Map<String, String> metadata;
	
	public Repo(String name, Map<String, String> metadata) {
		setName(name);
		setDescription(metadata.remove("description"));
		setType(metadata.remove("type"));
		setUrl(URLUtils.newUrl(metadata.remove("URL")));
		setEnable(Boolean.parseBoolean(metadata.remove("enable")));
		setMetadata(metadata);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "Repo [name=" + name + ", description=" + description + ", type=" + type + ", url=" + url + ", enable="
				+ enable + ", metadata=" + metadata + "]";
	}

}
