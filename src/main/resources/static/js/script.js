'use strict';

const btnShowText = 'Click to see mod list';
const btnHideText = 'Hide list';

function resizeFrame() {
    if (parent.length) {
        setTimeout(function () {
            const parentFrame = parent.document.getElementById(window.name);
            parentFrame.style.height = document.documentElement.offsetHeight + 'px';
            parentFrame.style.width = document.documentElement.scrollWidth + 'px';
        }, 10);
    }
}

function initModList(mods) {
    const ulNode = document.createElement('ul');
    ulNode.setAttribute('id', 'mod_list_ul');
    mods.forEach(element => {
        const liNode = document.createElement('li');
        const textNode = document.createTextNode(element.id + ' : ' + element.version);
        liNode.appendChild(textNode);
        ulNode.appendChild(liNode);
    });
    document.getElementById('mods_btn').onclick = () => {showMods(ulNode)};
}

function showMods(ulNode) {
    const modRov = document.getElementById('mod_row');
    const btn = document.getElementById('mods_btn');
    modRov.appendChild(ulNode);
    btn.onclick = () => {
        hideMods(ulNode)
    };
    btn.innerText = btnHideText;
    resizeFrame();
}

function hideMods(ulNode) {
    const modUl = document.getElementById('mod_list_ul');
    const btn = document.getElementById('mods_btn');
    modUl.remove();
    btn.innerText = btnShowText;
    btn.onclick = () => {
        showMods(ulNode)
    };
    resizeFrame();
}
