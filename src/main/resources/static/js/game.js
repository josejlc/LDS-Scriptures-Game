// Game JavaScript Functions
document.addEventListener('DOMContentLoaded', function() {

    // Option button hover effects
    const optionButtons = document.querySelectorAll('.option-btn');
    optionButtons.forEach(button => {
        button.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-2px)';
        });

        button.addEventListener('mouseleave', function() {
            this.style.transform = 'translateY(0)';
        });
    });

    // Auto-focus on player name input
    const playerNameInput = document.getElementById('playerName');
    if (playerNameInput) {
        playerNameInput.focus();
    }

    // Form validation
    const gameForm = document.querySelector('form[action*="/game/start"]');
    if (gameForm) {
        gameForm.addEventListener('submit', function(e) {
            const playerName = document.getElementById('playerName').value.trim();
            if (!playerName) {
                document.getElementById('playerName').value = 'Jugador';
            }
        });
    }

    // Smooth transitions
    document.body.style.opacity = '0';
    setTimeout(() => {
        document.body.style.transition = 'opacity 0.5s ease-in-out';
        document.body.style.opacity = '1';
    }, 100);
});