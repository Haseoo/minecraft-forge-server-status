'use strict';

function resizeFrame() {
    if (parent.length) {
        setTimeout(function () {
            const parentFrame = parent.document.getElementById(window.name);
            parentFrame.style.height = document.documentElement.offsetHeight + 'px';
            parentFrame.style.width = document.documentElement.scrollWidth + 'px';
        }, 10);
    }
}

function showMods(mods) {
    const modRov = document.getElementById('mod_row');
    const btn = document.getElementById('mods_btn');
    const ulNode = document.createElement('ul');
    ulNode.setAttribute('id', 'mod_list_ul');
    mods.forEach(element => {
        const liNode = document.createElement('li');
        const textNode = document.createTextNode(element.id + ' : ' + element.version);
        liNode.appendChild(textNode);
        ulNode.appendChild(liNode);
    });
    modRov.appendChild(ulNode);
    btn.onclick = () => {
        hideMods()
    };
    btn.innerText = 'Hide list';
    resizeFrame();
}

function hideMods() {
    const modUl = document.getElementById('mod_list_ul');
    const btn = document.getElementById('mods_btn');
    modUl.remove();
    btn.innerText = 'Click to see mod list';
    btn.onclick = () => {
        showMods(mods)
    };
    resizeFrame();
}
