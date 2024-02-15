/*
 * !!!!!!!!!!!!!!!!!!사용전 필독!!!!!!!!!!!!!!!!!!!!!
 * 유효성 적용 tag에 required, title 속성 필요
*/
let valChk = 0;

function formValidation(form) {
    valChk = 0;

    /* 메세지 중복 쌓임 방지 */
    for(let cls of document.getElementsByClassName('alert_class')) {
        // TODO 태그 삭제방식으로 변경
        //cls.remove();
        cls.innerHTML = '';
    }

    for(let elm of form.elements) {
        /* 조건 검사 */
        let validCase = elm.className.split(' ');
        if(elm.value.trim() !== '') {
            for(let classNm of validCase) {
                switch (classNm) {
                    case 'numOnly'      : validNum(elm); break;
                    case 'alphaOnly'    : validAlpha(elm); break;
                    case 'koreanOnly'   : validKorean(elm); break;
                    case 'idOnly'       : validId(elm); break;
                    case 'passOnly'     : validPass(elm); break;
                    case 'phoneOnly'    : validPhone(elm); break;
                    case 'emailOnly'    : validEmail(elm); break;
                }
            }
        }

        /* 필수 값 */
        if(elm.getAttribute('required') != null) {
            if(elm.type === 'radio' || elm.type === 'checkbox') {
                /* radio, checkbox label color rollback */
                elm.nextElementSibling.style.color = '#697a8d';
                if(document.querySelector('[name=\"' + elm.getAttribute('name') +'\"]:checked') === null) {
                    validationMsg('trim', elm);
                    valChk += 1;
                }
            } else {
                if(elm.value.trim() === '') {
                    validationMsg('trim', elm);
                    valChk += 1;
                }
            }
        }
    }
    return valChk <= 0;
}

function validNum(elm) {
    let patten = /(^[0-9]+$)/g;

    if(!patten.test(elm.value)) {
        valChk += 1;
        validationMsg('num', elm);
    }
}

function validAlpha(elm) {
    let patten = /(^[a-zA-Z]+$)/g;

    if(!patten.test(elm.value)) {
        valChk += 1;
        validationMsg('alpha', elm);
    }
}

function validKorean(elm) {
    let patten = /[ㄱ-힣]/;

    if(!patten.test(elm.value)) {
        valChk += 1;
        validationMsg('korean', elm);
    }
}

function validId(elm) {
    let patten = /^[a-z]+[a-z0-9]{5,19}$/g;

    if(!patten.test(elm.value)) {
        valChk += 1;
        validationMsg('id', elm);
    }
}

function validPass(elm) {
    let patten = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,16}$/g;

    if(!patten.test(elm.value)) {
        valChk += 1;
        validationMsg('pass', elm);
    }
}

function validPhone(elm) {
    let patten = /^01(?:0|1|[6-9])-(?:\d{3}|\d{4})-\d{4}$/g;

    if(!patten.test(elm.value)) {
        valChk += 1;
        validationMsg('phone', elm);
    }
}

function validEmail(elm) {
    let patten = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    if(!patten.test(elm.value)) {
        valChk += 1;
        validationMsg('email', elm);
    }
}

function validationMsg(divn, elm) {
    /* 경고문구 태그 속성 */
    let alert_el = document.createElement('strong');
    alert_el.id = 'alert_' + elm.getAttribute('id');
    alert_el.classList.add('alert_class');
    alert_el.style.color = '#ff3e1d';
    alert_el.style.fontSize = '85%';
    alert_el.style.fontFamily = 'inherit';
    alert_el.style.marginTop = '0.3rem';

    /* 경고문구 */
    let msg;
    switch (divn) {
        case 'trim' :
            msg = '은(는) 필수 ' + (elm.type === 'select-one' ? '선택' : '입력') + '입니다.\n';
            break;
        case 'num' :
            msg = ' (은)는 숫자만 입력할 수 있습니다.\n';
            break;
        case 'alpha' :
            msg = ' (은)는 알파벳(대, 소문자)만 입력할 수 있습니다.\n';
            break;
        case 'korean' :
            msg = ' (은)는 한글만 입력할 수 있습니다.\n';
            break;
        case 'id' :
            msg = ' (은)는 6 ~ 20자 알파벳(대, 소문자)와 숫자만 입력할 수 있습니다.\n';
            break;
        case 'pass' :
            msg = ' (은)는 8 ~ 16자 영문, 숫자, 특수문자를 최소 한가지씩 조합만 입력할 수 있습니다.\n';
            break;
        case 'phone' :
            msg = ' (은)는 숫자와 \'-\'만 입력할 수 있습니다.\n';
            break;
        case 'email' :
            msg = ' (은)는 \'@\'와 \'.\'이 포함되어야 입력할 수 있습니다.\n';
            break;
    }
    alert_el.innerText = elm.getAttribute('title') + msg;

    if(elm.type === 'radio' || elm.type === 'checkbox') {
        elm.nextElementSibling.style.color = '#ff3e1d';
    } else {
        /* 경고문구 붙이는 위치 */
        if(elm.parentNode.getAttribute('class') === 'input-group') {
            elm.parentNode.parentNode.appendChild(alert_el);
        } else {
            elm.parentNode.appendChild(alert_el);
        }
    }
}