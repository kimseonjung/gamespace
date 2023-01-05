let screenMode = "light";
function switchScreenMode() {
    const targetColor = (screenMode == "light") ? "dark" : "light";
    const targetString = [];
    targetString.push('-icon-cross');
    targetString.push('-icon-gamespace');
    targetString.push('-bg-screen');
    targetString.push('-bg-window');
    targetString.push('-placeholder');
    targetString.push('-text');

    targetString.forEach(target => {
        const targetClass = document.getElementsByClassName(screenMode + target);
        for (let j = targetClass.length - 1; j >= 0; j--) {
            targetClass[j].classList.replace(screenMode + target, targetColor + target);
        }
    });
    screenMode = targetColor;
}