function gfnCallAddList(url) {
    fetch(url)
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
        var scripts = frag.querySelectorAll('script'), script, fixedScript, i, len;

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