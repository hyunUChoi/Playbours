/*
 * !!!!!!!!!!!!!!!!!!사용전 필독!!!!!!!!!!!!!!!!!!!!!
 * 유효성 적용 tag에 required, title 속성 필요
*/
let spacePatten = /\s/g;
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
                    case 'phoneOnly'    : validEmail(elm); break;
                    case 'emailOnly'    : validPhone(elm); break;
                }
            }
        }

        /* 필수 값 */
        if(elm.getAttribute('required') != null) {
            if(elm.value.trim() === '') {
                validationMsg('trim', elm);
                valChk += 1;
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
    let patten = /(^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]+$)/g;

    if(!patten.test(elm.value)) {
        valChk += 1;
        validationMsg('korean', elm);
    }
}

function validId(elm) {
    let patten = /(^[a-zA-Z0-9]+$)/g;

    if(!patten.test(elm.value)) {
        valChk += 1;
        validationMsg('id', elm);
    }
}

function validPhone(elm) {
    let patten = /(^[a-zA-Z]+$)/g;

    if(!patten.test(elm.value)) {
        valChk += 1;
        validationMsg('phone', elm);
    }
}

function validEmail(elm) {
    let patten = /(^[a-zA-Z]+$)/g;

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
        case 'id' :
            msg = ' (은)는 알파벳(대, 소문자)와 숫자만 입력할 수 있습니다.\n';
            break;
    }
    alert_el.innerText = elm.getAttribute('title') + msg;

    /* 경고문구 붙이는 위치 */
    if(elm.parentNode.getAttribute('class') === 'input-group') {
        elm.parentNode.parentNode.appendChild(alert_el);
    } else {
        elm.parentNode.appendChild(alert_el);
    }

}