package com.kjawank_jose.lds_scriptures_game.service;

import com.kjawank_jose.lds_scriptures_game.model.Verse;
import com.kjawank_jose.lds_scriptures_game.model.ScriptureBook;
import com.kjawank_jose.lds_scriptures_game.enums.ScriptureBookType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class VerseService {

    private List<Verse> verses;
    private List<ScriptureBook> books;
    private Random random = new Random();

    public VerseService() {
        initializeData();
    }

    private void initializeData() {
        books = new ArrayList<>();
        verses = new ArrayList<>();

        // Crear libros
        ScriptureBook oldTestament = new ScriptureBook(1L, "Antiguo Testamento", "OLD_TESTAMENT");
        ScriptureBook newTestament = new ScriptureBook(2L, "Nuevo Testamento", "NEW_TESTAMENT");
        ScriptureBook bookOfMormon = new ScriptureBook(3L, "Libro de Mormón", "BOOK_OF_MORMON");
        ScriptureBook doctrineCovenants = new ScriptureBook(4L, "Doctrina y Convenios e Historia de la Iglesia", "DOCTRINE_COVENANTS");

        books.add(oldTestament);
        books.add(newTestament);
        books.add(bookOfMormon);
        books.add(doctrineCovenants);

        // Agregar versículos (ejemplo con uno por libro)
        //verses.add(new Verse("… esta es mi obra y mi gloria: Llevar a cabo la inmortalidad y la vida eterna del hombre", "Moisés 1:39", oldTestament));
        //verses.add(new Verse("Así alumbre vuestra luz delante de los hombres", "Mateo 5:14–16", newTestament));
        //verses.add(new Verse("Iré y haré lo que el Señor ha mandado", "1 Nefi 3:7", bookOfMormon));
        //verses.add(new Verse("la única iglesia verdadera y viviente", "Doctrina y Convenios 1:30", doctrineCovenants));

        // ANTIGUO TESTAMENTO
        verses.add(new Verse("… esta es mi obra y mi gloria: Llevar a cabo la inmortalidad y la vida eterna del hombre", "Moisés 1:39", oldTestament));
        verses.add(new Verse("Y el Señor llamó Sion a su pueblo, porque eran uno en corazón y voluntad", "Moisés 7:18", oldTestament));
        verses.add(new Verse("El Señor prometió a Abraham que su descendencia llevaría 'este ministerio y sacerdocio a todas las naciones'", "Abraham 2:9–11", oldTestament));
        verses.add(new Verse("Como espíritus, fuimos 'organizad[o]s antes que existiera el mundo'", "Abraham 3:22–23", oldTestament));
        verses.add(new Verse("… creó Dios al hombre a su imagen", "Génesis 1:26–27", oldTestament));
        verses.add(new Verse("… el hombre […] se allegará a su mujer, y serán una sola carne", "Génesis 2:24", oldTestament));
        verses.add(new Verse("… ¿cómo, pues, haría yo este gran mal y pecaría contra Dios?", "Génesis 39:9", oldTestament));
        verses.add(new Verse("Los Diez Mandamientos", "Éxodo 20:3–17", oldTestament));
        verses.add(new Verse("… escogeos hoy a quién sirváis", "Josué 24:15", oldTestament));
        verses.add(new Verse("¿Y quién estará en su lugar santo? El limpio de manos y puro de corazón", "Salmos 24:3–4", oldTestament));
        verses.add(new Verse("Confía en Jehová con todo tu corazón […] y él enderezará tus veredas", "Proverbios 3:5–6", oldTestament));
        verses.add(new Verse("… aunque vuestros pecados sean como la grana, como la nieve serán emblanquecidos", "Isaías 1:18", oldTestament));
        verses.add(new Verse("Ay de los que a lo malo llaman bueno, y a lo bueno, malo", "Isaías 5:20", oldTestament));
        verses.add(new Verse("La restauración del Evangelio es 'una obra maravillosa y un prodigio'", "Isaías 29:13–14", oldTestament));
        verses.add(new Verse("Ciertamente llevó [Jesucristo] nuestras enfermedades y sufrió nuestros dolores", "Isaías 53:3–5", oldTestament));
        verses.add(new Verse("Las bendiciones del ayuno apropiado", "Isaías 58:6–7", oldTestament));
        verses.add(new Verse("[Retráete] […] de hacer tu voluntad en mi día santo, y [llámalo] delicia", "Isaías 58:13–14", oldTestament));
        verses.add(new Verse("Antes que te formase en el vientre […] te di por profeta a las naciones", "Jeremías 1:4–5", oldTestament));
        verses.add(new Verse("El profeta es 'atalaya a la casa de Israel'", "Ezequiel 3:16–17", oldTestament));
        verses.add(new Verse("La Biblia y el Libro de Mormón 'serán uno solo en tu mano'", "Ezequiel 37:15–17", oldTestament));
        verses.add(new Verse("Dios 'levantará un reino que no será jamás destruido'", "Daniel 2:44–45", oldTestament));
        verses.add(new Verse("… Jehová el Señor […] revel[a] su secreto a sus siervos los profetas", "Amós 3:7", oldTestament));
        verses.add(new Verse("Las bendiciones de pagar el diezmo", "Malaquías 3:8–10", oldTestament));
        verses.add(new Verse("Elías 'hará volver […] el corazón de los hijos hacia los padres'", "Malaquías 4:5–6", oldTestament));

// NUEVO TESTAMENTO
        verses.add(new Verse("Así alumbre vuestra luz delante de los hombres", "Mateo 5:14–16", newTestament));
        verses.add(new Verse("Venid a mí todos los que estáis trabajados y cargados, y yo os haré descansar", "Mateo 11:28–30", newTestament));
        verses.add(new Verse("Jesús dijo: '… a ti te daré las llaves del reino de los cielos'", "Mateo 16:15–19", newTestament));
        verses.add(new Verse("Amarás al Señor tu Dios […]. Amarás a tu prójimo", "Mateo 22:36–39", newTestament));
        verses.add(new Verse("[Q]ue os ha nacido hoy, en la ciudad de David, un Salvador, que es CRISTO el Señor", "Lucas 2:10–12", newTestament));
        verses.add(new Verse("Jesucristo mandó que participáramos de la Santa Cena 'en memoria de [Él]'", "Lucas 22:19–20", newTestament));
        verses.add(new Verse("… porque un espíritu no tiene carne ni huesos como veis que yo tengo", "Lucas 24:36–39", newTestament));
        verses.add(new Verse("… el que no naciere de agua y del Espíritu no puede entrar en el reino de Dios", "Juan 3:5", newTestament));
        verses.add(new Verse("Porque de tal manera amó Dios al mundo que ha dado a su Hijo Unigénito", "Juan 3:16", newTestament));
        verses.add(new Verse("El que quiera hacer la voluntad de él conocerá si la doctrina es de Dios", "Juan 7:17", newTestament));
        verses.add(new Verse("Y esta es la vida eterna: que te conozcan a ti, el único Dios verdadero, y a Jesucristo", "Juan 17:3", newTestament));
        verses.add(new Verse("… vuestro cuerpo es templo del Espíritu Santo", "1 Corintios 6:19–20", newTestament));
        verses.add(new Verse("Pero en el Señor, ni el varón es sin la mujer, ni la mujer sin el varón", "1 Corintios 11:11", newTestament));
        verses.add(new Verse("… así como en Adán todos mueren, así también en Cristo todos serán vivificados", "1 Corintios 15:20–22", newTestament));
        verses.add(new Verse("En la resurrección hay tres grados de gloria", "1 Corintios 15:40–42", newTestament));
        verses.add(new Verse("Dios reunirá 'todas las cosas en Cristo, en la dispensación del cumplimiento de los tiempos'", "Efesios 1:10", newTestament));
        verses.add(new Verse("La Iglesia está edificada 'sobre el fundamento de los apóstoles y profetas, siendo la principal piedra del ángulo Jesucristo mismo'", "Efesios 2:19–20", newTestament));
        verses.add(new Verse("… el día del Señor […] no vendrá sin que antes venga la apostasía", "2 Tesalonicenses 2:1–3", newTestament));
        verses.add(new Verse("… las Sagradas Escrituras […] te pueden hacer sabio para la salvación", "2 Timoteo 3:15–17", newTestament));
        verses.add(new Verse("El Padre Celestial es el 'Padre de los espíritus'", "Hebreos 12:9", newTestament));
        verses.add(new Verse("Y si alguno de vosotros tiene falta de sabiduría, pídala a Dios", "Santiago 1:5–6", newTestament));
        verses.add(new Verse("… la fe, si no tiene obras, es muerta", "Santiago 2:17–18", newTestament));
        verses.add(new Verse("… también ha sido predicado el evangelio a los muertos", "1 Pedro 4:6", newTestament));
        verses.add(new Verse("… Y fueron juzgados los muertos […] según sus obras", "Apocalipsis 20:12", newTestament));

// LIBRO DE MORMÓN
        verses.add(new Verse("Iré y haré lo que el Señor ha mandado", "1 Nefi 3:7", bookOfMormon));
        verses.add(new Verse("Adán cayó para que los hombres existiesen; y existen los hombres para que tengan gozo", "2 Nefi 2:25", bookOfMormon));
        verses.add(new Verse("son libres para escoger la libertad y la vida eterna, o la cautividad y la muerte", "2 Nefi 2:27", bookOfMormon));
        verses.add(new Verse("todos son iguales ante Dios", "2 Nefi 26:33", bookOfMormon));
        verses.add(new Verse("Dios dará a los hijos de los hombres línea por línea, precepto por precepto", "2 Nefi 28:30", bookOfMormon));
        verses.add(new Verse("Deleitaos en las palabras de Cristo; porque he aquí, las palabras de Cristo os dirán todas las cosas que debéis hacer", "2 Nefi 32:3", bookOfMormon));
        verses.add(new Verse("debéis orar siempre", "2 Nefi 32:8–9", bookOfMormon));
        verses.add(new Verse("cuando os halláis al servicio de vuestros semejantes, solo estáis al servicio de vuestro Dios", "Mosíah 2:17", bookOfMormon));
        verses.add(new Verse("aquellos que guardan los mandamientos de Dios son bendecidos en todas las cosas", "Mosíah 2:41", bookOfMormon));
        verses.add(new Verse("despoj[aos] del hombre natural, y [haceos] santo[s] por la expiación de Cristo el Señor", "Mosíah 3:19", bookOfMormon));
        verses.add(new Verse("Creed en Dios; creed que él tiene toda sabiduría", "Mosíah 4:9", bookOfMormon));
        verses.add(new Verse("bautiza[os] en el nombre del Señor, como testimonio de que habéis concertado un convenio con él", "Mosíah 18:8–10", bookOfMormon));
        verses.add(new Verse("Y él saldrá, sufriendo dolores, aflicciones y tentaciones de todas clases", "Alma 7:11–13", bookOfMormon));
        verses.add(new Verse("es necesario que se realice una expiación, un sacrificio infinito y eterno", "Alma 34:9–10", bookOfMormon));
        verses.add(new Verse("no te dej[es] llevar más por las concupiscencias de tus ojos", "Alma 39:9", bookOfMormon));
        verses.add(new Verse("la maldad nunca fue felicidad", "Alma 41:10", bookOfMormon));
        verses.add(new Verse("es sobre la roca de nuestro Redentor donde debéis establecer vuestro fundamento", "Helamán 5:12", bookOfMormon));
        verses.add(new Verse("me he sometido a la voluntad del Padre en todas las cosas desde el principio", "3 Nefi 11:10–11", bookOfMormon));
        verses.add(new Verse("[sed] perfectos así como yo, o como vuestro Padre que está en los cielos es perfecto", "3 Nefi 12:48", bookOfMormon));
        verses.add(new Verse("venid a mí y sed bautizados, para que seáis santificados por la recepción del Espíritu Santo", "3 Nefi 27:20", bookOfMormon));
        verses.add(new Verse("no recibís ningún testimonio sino hasta después de la prueba de vuestra fe", "Éter 12:6", bookOfMormon));
        verses.add(new Verse("si los hombres vienen a mí, entonces haré que las cosas débiles sean fuertes para ellos", "Éter 12:27", bookOfMormon));
        verses.add(new Verse("la caridad es el amor puro de Cristo", "Moroni 7:45–48", bookOfMormon));
        verses.add(new Verse("[pedid] con un corazón sincero, con verdadera intención, teniendo fe en Cristo; y por el poder del Espíritu Santo podréis conocer la verdad de todas las cosas", "Moroni 10:4–5", bookOfMormon));

// DOCTRINA Y CONVENIOS
        verses.add(new Verse("José Smith vio a dos Personajes, cuyo fulgor y gloria no admiten descripción", "José Smith—Historia 1:15–20", doctrineCovenants));
        verses.add(new Verse("la única iglesia verdadera y viviente", "Doctrina y Convenios 1:30", doctrineCovenants));
        verses.add(new Verse("sea por mi propia voz o por la voz de mis siervos, es lo mismo", "Doctrina y Convenios 1:37–38", doctrineCovenants));
        verses.add(new Verse("Mirad hacia mí en todo pensamiento; no dudéis; no temáis", "Doctrina y Convenios 6:36", doctrineCovenants));
        verses.add(new Verse("hablaré a tu mente y a tu corazón por medio del Espíritu Santo", "Doctrina y Convenios 8:2–3", doctrineCovenants));
        verses.add(new Verse("El Sacerdocio Aarónico tiene las llaves del ministerio de ángeles, y del evangelio de arrepentimiento, y del bautismo", "Doctrina y Convenios 13:1", doctrineCovenants));
        verses.add(new Verse("el valor de las almas es grande a la vista de Dios", "Doctrina y Convenios 18:10–11", doctrineCovenants));
        verses.add(new Verse("¡cuán grande no será vuestro gozo si me trajereis muchas almas!", "Doctrina y Convenios 18:15–16", doctrineCovenants));
        verses.add(new Verse("yo, Jesucristo, he padecido estas cosas por todos", "Doctrina y Convenios 19:16–19", doctrineCovenants));
        verses.add(new Verse("recibiréis la palabra del profeta como si viniera de mi propia boca", "Doctrina y Convenios 21:4–6", doctrineCovenants));
        verses.add(new Verse("con poder y gran gloria me revelaré desde los cielos y moraré en rectitud con los hombres sobre la tierra mil años", "Doctrina y Convenios 29:10–11", doctrineCovenants));
        verses.add(new Verse("el matrimonio lo decretó Dios", "Doctrina y Convenios 49:15–17", doctrineCovenants));
        verses.add(new Verse("quien se ha arrepentido de sus pecados es perdonado", "Doctrina y Convenios 58:42–43", doctrineCovenants));
        verses.add(new Verse("a vosotros os es requerido perdonar a todos los hombres", "Doctrina y Convenios 64:9–11", doctrineCovenants));
        verses.add(new Verse("por Jesucristo los mundos son y fueron creados", "Doctrina y Convenios 76:22–24", doctrineCovenants));
        verses.add(new Verse("Yo, el Señor, estoy obligado cuando hacéis lo que os digo", "Doctrina y Convenios 82:10", doctrineCovenants));
        verses.add(new Verse("en sus ordenanzas se manifiesta el poder de la divinidad", "Doctrina y Convenios 84:20–22", doctrineCovenants));
        verses.add(new Verse("buscad conocimiento, tanto por el estudio como por la fe", "Doctrina y Convenios 88:118", doctrineCovenants));
        verses.add(new Verse("Las bendiciones de vivir la Palabra de Sabiduría", "Doctrina y Convenios 89:18–21", doctrineCovenants));
        verses.add(new Verse("El Sacerdocio de Melquisedec tiene poder y autoridad para administrar en las cosas espirituales", "Doctrina y Convenios 107:8", doctrineCovenants));
        verses.add(new Verse("los derechos del sacerdocio no pueden ser gobernados ni manejados sino conforme a los principios de la rectitud", "Doctrina y Convenios 121:36, 41–42", doctrineCovenants));
        verses.add(new Verse("Dios ha revelado su poder, su gloria y su amor en todas las cosas", "Doctrina y Convenios 130:18–19", doctrineCovenants));
        verses.add(new Verse("si somos fieles, seremos exaltados", "Doctrina y Convenios 132:19", doctrineCovenants));


        // ... resto de versículos según el formato del data.sql
    }

    public List<Verse> getAllVerses() {
        return new ArrayList<>(verses);
    }

    public List<Verse> getVersesByBook(ScriptureBookType bookType) {
        if (bookType == ScriptureBookType.ALL_BOOKS) {
            return getAllVerses();
        }

        return verses.stream()
                .filter(verse -> verse.getBook().getName().equals(bookType.getName()))
                .collect(Collectors.toList());
    }

    public Verse getRandomVerse() {
        if (verses.isEmpty()) return null;
        return verses.get(random.nextInt(verses.size()));
    }

    public Verse getRandomVerseFromBook(ScriptureBookType bookType) {
        List<Verse> bookVerses = getVersesByBook(bookType);
        if (bookVerses.isEmpty()) return null;
        return bookVerses.get(random.nextInt(bookVerses.size()));
    }

    public List<ScriptureBook> getAllBooks() {
        return new ArrayList<>(books);
    }
}
