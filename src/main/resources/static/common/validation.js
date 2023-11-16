/*
 * !!!!!!!!!!!!!!!!!!사용전 필독!!!!!!!!!!!!!!!!!!!!!
 * 유효성 적용 tag에 required, title 속성 필요
*/
let spacePatten = /\s/g;
let valChk = 0;

function formValidation(form) {

    /* 메세지 중복 쌓임 방지 */
    for(let cls of document.getElementsByClassName('alert_class')) {
        // TODO 태그 삭제방식으로 변경
        // cls.remove();
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
                }
            }
        }

        /* 입력값 중 사이 공백 검사 */
        if(elm.type !== 'textarea' && spacePatten.test(elm.value)) {
            validationMsg('space', elm);
            valChk += 1;
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

function validationMsg(divn, elm) {
    let alert_el = document.createElement('p');
    alert_el.id = 'alert_' + elm.getAttribute('id');
    alert_el.classList.add('alert_class');
    alert_el.style.color = '#ff3e1d';
    alert_el.style.fontSize = '85%';
    alert_el.style.fontFamily = 'inherit';
    alert_el.style.marginTop = '0.3rem';

    if(divn === 'trim') {
        alert_el.innerText = elm.getAttribute('title') + '은(는) 필수 ' + (elm.type === 'select-one' ? '선택' : '입력') + '입니다.';
    } else if(divn === 'space') {
        alert_el.innerText = elm.getAttribute('title') + ' 값에 공백이 포함되어 있습니다. 다시 입력해주세요.';
    } else if(divn === 'num') {
        alert_el.innerText = elm.getAttribute('title') + ' (은)는 숫자만 입력할 수 있습니다.';
    } else if(divn === 'alpha') {
        alert_el.innerText = elm.getAttribute('title') + ' (은)는 알파벳(대, 소문자)만 입력할 수 있습니다.';
    }

    if(elm.parentNode.getAttribute('class') === 'input-group') {
        elm.parentNode.parentNode.appendChild(alert_el);
    } else {
        elm.parentNode.appendChild(alert_el);
    }

}