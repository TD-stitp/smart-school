function randomValueFromArray(array) {
  const randomNo = Math.floor(Math.random() * array.length);
  return array[randomNo];
}

// Register service worker to control making site work offline

if ('serviceWorker' in navigator) {
  navigator.serviceWorker
    .register('/sw.js')
    .then(() => console.log('Service worker registered successfully!'))
    .catch(() => console.log('Service worker failed to register!'))
}

// Code to handle install prompt on desktop

let deferredPrompt;
window.addEventListener('beforeinstallprompt', (e) => {
  // Prevent Chrome 67 and earlier from automatically showing the prompt
  e.preventDefault();
  // Stash the event so it can be triggered later.
  deferredPrompt = e;
  // Update UI to notify the user they can add to home screen
})
