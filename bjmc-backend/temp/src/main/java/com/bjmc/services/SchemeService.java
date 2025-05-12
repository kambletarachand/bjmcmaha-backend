package com.bjmc.services;


import org.springframework.stereotype.Service;

import com.bjmc.entities.Scheme;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchemeService {

    public List<Scheme> getLabourSchemes() {
        List<Scheme> schemes = new ArrayList<>();
        schemes.add(new Scheme(
                "Employees' State Insurance Scheme (ESIC)",
                "Health and social security insurance for Indian workers.",
                "https://www.esic.nic.in/"
        ));
        schemes.add(new Scheme(
                "Employees' Provident Fund (EPF)",
                "Retirement savings scheme for salaried employees.",
                "https://www.epfindia.gov.in/"
        ));
        return schemes;
    }

    public List<Scheme> getPradhanMantriYojanas() {
        List<Scheme> schemes = new ArrayList<>();
        schemes.add(new Scheme(
                "Pradhan Mantri Awas Yojana (PMAY)",
                "Affordable housing for urban and rural poor.",
                "https://pmaymis.gov.in/"
        ));
        schemes.add(new Scheme(
                "Pradhan Mantri Shram Yogi Maan-Dhan (PM-SYM)",
                "Pension scheme for unorganized workers.",
                "https://maandhan.in"
        ));
        return schemes;
    }

    public List<Scheme> getGovtJobCards() {
        List<Scheme> jobs = new ArrayList<>();
        jobs.add(new Scheme(
                "MGNREGA Job Card",
                "Provides 100 days of wage employment to rural households.",
                "https://nrega.nic.in/netnrega/home.aspx"
        ));
        jobs.add(new Scheme(
                "National Career Service (NCS)",
                "Central government portal for job seekers and skill development.",
                "https://www.ncs.gov.in/"
        ));
        jobs.add(new Scheme(
                "Apprenticeship India Portal",
                "Apprenticeship training opportunities with government recognition.",
                "https://www.apprenticeshipindia.gov.in/"
        ));
        jobs.add(new Scheme(
                "Skill India – PMKVY",
                "Skill training and certification for employment opportunities.",
                "https://www.pmkvyofficial.org/"
        ));
        return jobs;
    }
}
