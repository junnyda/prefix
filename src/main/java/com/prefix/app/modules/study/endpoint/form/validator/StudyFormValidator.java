package com.prefix.app.modules.study.endpoint.form.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.prefix.app.modules.study.endpoint.form.StudyForm;
import com.prefix.app.modules.study.infra.repository.StudyRepository;

@Component
@RequiredArgsConstructor
public class StudyFormValidator implements Validator {
    private final StudyRepository studyRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return StudyForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudyForm studyForm = (StudyForm) target;
        if (studyRepository.existsByPath(studyForm.getPath())) {
            errors.rejectValue("path", "wrong.path", "이미 사용중인 스터디 경로입니다.");
        }
    }
}
