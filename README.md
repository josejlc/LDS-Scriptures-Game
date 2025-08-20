# 📚 Juego de las Escrituras LDS - Seminario

Una aplicación web interactiva diseñada para ayudar a los estudiantes de Seminario de La Iglesia de Jesucristo de los Santos de los Últimos Días a estudiar y memorizar las escrituras de manera divertida y gamificada.

## 🎯 Características Principales

- **🎮 Juego Interactivo**: Sistema de preguntas y respuestas sobre escrituras
- **👤 Perfiles de Usuario**: Configuración personalizada por año de seminario
- **🏆 Sistema de Puntuación**: Leaderboard competitivo entre estudiantes
- **📊 Historial Detallado**: Seguimiento de progreso y estadísticas
- **📱 Diseño Responsivo**: Optimizado para móviles, tablets y desktop
- **🎨 Interfaz Moderna**: Diseño con efectos visuales atractivos
- **♿ Accesible**: Soporte para lectores de pantalla y navegación por teclado

## 🛠️ Tecnologías Utilizadas

### Backend
- **Java**: Lenguaje principal
- **Spring Boot**: Framework web
- **Thymeleaf**: Motor de plantillas
- **Spring Data JPA**: Persistencia de datos
- **H2 Database**: Base de datos en memoria (desarrollo)
- **Maven**: Gestión de dependencias

### Frontend
- **HTML5**: Estructura semántica
- **CSS3**: Estilos modernos con variables CSS
- **Bootstrap 5.3**: Framework CSS responsivo
- **JavaScript**: Interactividad y validación
- **Bootstrap Icons**: Iconografía

### Características Avanzadas
- **CSS Custom Properties**: Variables para temas consistentes
- **Backdrop Filter**: Efectos de cristal esmerilado
- **CSS Grid & Flexbox**: Layouts modernos
- **Transiciones Suaves**: Animaciones fluidas
- **Media Queries**: Diseño totalmente responsivo
- **Modo Oscuro**: Soporte automático según preferencias del sistema

## 🚀 Instalación y Configuración

### Prerrequisitos
- Java 11 o superior
- Maven 3.6 o superior
- Git

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/josejlc/LDS-Scriptures-Game.git
   cd juego-escrituras-lds
   ```

2. **Compilar el proyecto**
   ```bash
   mvn clean compile
   ```

3. **Ejecutar la aplicación**
   ```bash
   mvn spring-boot:run
   ```

4. **Acceder a la aplicación**
   - Abrir navegador en: `http://localhost:8080`
   - La aplicación estará lista para usar

## 📁 Estructura del Proyecto

```
LDS-Scriptures-Game/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/kjawank_jose/LDS-Scriptures-Game/
│   │   │       ├── controller/          # Controladores web
│   │   │       ├── model/               # Entidades y DTOs
│   │   │       ├── service/             # Lógica de negocio
│   │   │       ├── repository/          # Acceso a datos
│   │   │       └── JuegoEscriturasApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   └── game.css         # Estilos principales
│   │       │   └── js/
│   │       │       └── game.js          # JavaScript custom
│   │       ├── templates/               # Plantillas Thymeleaf
│   │       │   ├── index.html          # Página principal
│   │       │   ├── game.html           # Interfaz del juego
│   │       │   ├── leaderboard.html    # Tabla de puntuaciones
│   │       │   └── history.html        # Historial de partidas
│   │       └── application.properties   # Configuración
├── pom.xml                             # Dependencias Maven
└── README.md                           # Este archivo
```

## 🎮 Cómo Usar la Aplicación

### 1. **Configurar Perfil**
- Ingresa tu nombre completo
- Selecciona tu año de seminario (S1-S4)
- Haz clic en "Empezar el Juego"

### 2. **Jugar**
- Lee la escritura mostrada
- Selecciona la respuesta correcta entre las opciones
- Recibe feedback inmediato
- Acumula puntos por respuestas correctas

### 3. **Ver Progreso**
- **🏆 Puntuaciones**: Compite en el leaderboard
- **📊 Historial**: Revisa tus partidas anteriores
- **📈 Estadísticas**: Monitorea tu progreso

## 🎨 Diseño y UX

### Paleta de Colores
```css
--primary-color: #0d6efd    /* Azul principal */
--success-color: #198754    /* Verde éxito */
--warning-color: #ffc107    /* Amarillo advertencia */
--info-color: #0dcaf0       /* Cyan información */
--gradient-primary: linear-gradient(135deg, #fefefe 0%, #f9f9f9 100%)
```

### Efectos Visuales
- **Gradiente de Fondo**: Sutil transición blanco a gris claro
- **Efecto Cristal**: Tarjetas con `backdrop-filter: blur()`
- **Animaciones Hover**: Elementos que reaccionan al cursor
- **Transiciones Suaves**: Cambios fluidos entre estados
- **Efectos de Onda**: Botones con animaciones expansivas

### Responsive Design
- **Mobile First**: Optimizado primero para móviles
- **Breakpoints**: sm(576px), md(768px), lg(992px), xl(1200px)
- **Grid Adaptativo**: Columnas que se reorganizan según pantalla
- **Tipografía Escalable**: Tamaños que se ajustan al dispositivo

## 🔧 Configuración Avanzada

### Base de Datos
```properties
# Desarrollo (H2 en memoria)
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true

# Producción (ejemplo con MySQL)
# spring.datasource.url=jdbc:mysql://localhost:3306/escrituras_lds
# spring.datasource.username=usuario
# spring.datasource.password=password
```

### Personalización de Estilos
1. Modificar variables CSS en `/static/css/game.css`:
   ```css
   :root {
       --primary-color: #tu-color;
       --gradient-primary: tu-gradiente;
   }
   ```

2. Los cambios se reflejan automáticamente en toda la aplicación

## 🧪 Testing

```bash
# Ejecutar tests
mvn test

# Ejecutar tests con coverage
mvn test jacoco:report
```

## 📦 Despliegue

### Compilar JAR
```bash
mvn clean package
java -jar target/juego-escrituras-lds-1.0.jar
```

### Docker (opcional)
```dockerfile
FROM openjdk:11-jre-slim
COPY target/juego-escrituras-lds-1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 🤝 Contribuir al Proyecto

1. **Fork** el repositorio
2. **Crear** una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. **Commit** tus cambios (`git commit -m 'Añadir nueva funcionalidad'`)
4. **Push** a la rama (`git push origin feature/nueva-funcionalidad`)
5. **Crear** un Pull Request

### Estándares de Código
- **Java**: Seguir convenciones de Spring Boot
- **CSS**: Usar variables CSS y comentarios descriptivos
- **HTML**: Estructura semántica y accesible
- **JavaScript**: ES6+ con comentarios claros

## 🐛 Reportar Issues

Si encuentras algún problema:
1. Verifica que no esté ya reportado en [Issues](../../issues)
2. Crea un nuevo issue con:
   - Descripción detallada del problema
   - Pasos para reproducir
   - Capturas de pantalla (si aplica)
   - Información del navegador/sistema

## 📋 Roadmap

### Versión Actual (1.0)
- ✅ Sistema básico de preguntas
- ✅ Leaderboard funcional
- ✅ Diseño responsivo
- ✅ Historial de partidas

### Próximas Versiones
- 🔄 **v1.1**: Categorías de preguntas por libro
- 🔄 **v1.2**: Modo multijugador en tiempo real
- 🔄 **v1.3**: Sistema de logros y badges
- 🔄 **v1.4**: Exportar progreso a PDF
- 🔄 **v1.5**: Integración con calendario de estudio

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver [LICENSE](LICENSE) para más detalles.

## 🙏 Reconocimientos

- **Bootstrap Team**: Por el framework CSS
- **Spring Team**: Por el framework Java
- **Iconos**: Bootstrap Icons
- **Inspiración**: Comunidad educativa LDS

## 📞 Contacto

- **Autor**: Tu Nombre
- **Email**: tu.email@ejemplo.com
- **GitHub**: [@tuusuario](https://github.com/josejlc)
- **LinkedIn**: [Tu Perfil](https://www.linkedin.com/in/josecajahuanca/)

---

<div align="center">

**¡Hecho con ❤️ para la comunidad educativa LDS!**

[⬆ Volver al inicio](#-juego-de-las-escrituras-lds---seminario)

</div>
