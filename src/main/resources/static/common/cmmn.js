function gfnCallAddList(url, val, valNm) {
    fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            page: val,
            menuCd: valNm
        })
    })
        .then(function(res) {
            res.text().then(function(html) {
                let html_dom = new DOMParser().parseFromString(html,'text/html'),
                    frag = document.createDocumentFragment();

                for(let node of html_dom.body.childNodes) {
                    frag.appendChild(node);
                }

                fixScriptsSoTheyAreExecuted(frag);
                document.getElementById("tbl").append(frag);
            })
        });

    /* script 활성화 */
    function fixScriptsSoTheyAreExecuted(frag) {
        let scripts = frag.querySelectorAll('script'), script, fixedScript, i, len;

        for (i = 0, len = scripts.length; i < len; i++) {
            script = scripts[i];

            fixedScript = document.createElement('script');
            fixedScript.type = script.type;
            if (script.innerHTML) fixedScript.innerHTML = script.innerHTML;
            else fixedScript.src = script.src;
            fixedScript.async = false;

            script.parentNode.replaceChild(fixedScript, script);
        }
    }
}

function gfnPageProcess(divn, url, val, valNm) {
    let frm = document.getElementById("defaultFrm");
    let path = window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/') + 1);
    frm.setAttribute("action", path + url);

    switch (divn) {
        case 'list' :
            frm.submit();
            break;

        case 'addList' :
            gfnCallAddList(path + url, val, valNm);
            break;

        case 'view' :
            document.getElementById(valNm).value = val;
            frm.submit();
            break;

        case 'insert' :
            frm.submit();
            break;

        case 'update' :
            if(val === undefined) {
                alert("수정되었습니다.");
            } else {
                alert(val);
            }
            frm.submit();
            break;

        case 'submit' :
            if(formValidation(frm)) {
                if(val === undefined) {
                    alert("등록되었습니다.");
                } else {
                    alert(val);
                }
                frm.submit();
            }
            break;

        case 'delete' :
            if(confirm("삭제하시겠습니까?")) {
                alert("삭제되었습니다.");
                frm.submit();
            } else {
                return false;
            }
            break;
    }
}