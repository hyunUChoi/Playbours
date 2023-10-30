let spacePatten = /\s/g;

function formValidation(form) {
    let valid = [], alertMsg;
    for(let elm of form.elements) {
        if(elm.getAttribute('required') != null) {
            // 입력값 중 사이 공백 검사
            if(spacePatten.test(elm.value)) {
                // TODO 공백입력 경고창
                valid.push(false);
            }

            // 필수값 미입력
            if(elm.value.trim() === '') {
                let alert_el = document.createElement('strong');
                alert_el.id = 'alert_' + elm.getAttribute('id');
                alert_el.style.color = '#ff3e1d';
                alert_el.style.fontSize = '85%';
                alert_el.style.marginTop = '0.3rem';
                alert_el.innerText = elm.type === 'select-one' ? elm.getAttribute('title') + '은(는) 필수 ' : '';
                console.log(alert_el)
                valid.push(false);
            }
        }
    }
}