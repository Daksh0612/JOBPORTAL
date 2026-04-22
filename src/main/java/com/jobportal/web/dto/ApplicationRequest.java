package com.jobportal.web.dto;

import javax.validation.constraints.NotBlank;

public class ApplicationRequest {

    @NotBlank
    private String coverLetter;

    @NotBlank
    private String resumeLink;

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public String getResumeLink() {
        return resumeLink;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }
}
