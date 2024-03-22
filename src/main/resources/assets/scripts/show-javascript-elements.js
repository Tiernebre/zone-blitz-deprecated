const showJavaScriptOnlyElements = () => {
  document.querySelectorAll(".js-only").forEach((node) => {
    node.classList.remove("js-only");
  });
};

document.addEventListener("DOMContentLoaded", showJavaScriptOnlyElements);
showJavaScriptOnlyElements();
