using Microsoft.AspNetCore.Identity;
using QuizApi.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.Data
{
    public class ProjectDataInitializer
    {
        private readonly ApplicationDbContext _dbContext;
        private readonly UserManager<IdentityUser> _userManager;

        public ProjectDataInitializer(ApplicationDbContext dbContext, UserManager<IdentityUser> userManager)
        {
            _dbContext = dbContext;
            _userManager = userManager;
        }

        public async Task InitializeDataAsync()
        {
            _dbContext.Database.EnsureDeleted();
            if (_dbContext.Database.EnsureCreated())
            {
        Quiz quiz3 = new Quiz("Quiz1", "Geografie");
                quiz3.Id = 1;
                MeerkeuzeVraag m1 = new MeerkeuzeVraag(1, "Wat is Andorra?", "een natuurkundige", "een stad in Mexico", "een drankje", "een staat in Europa");
                MeerkeuzeVraag m2 = new MeerkeuzeVraag(1, "Hoe heet de hoofdstad van Zwitserland?", "Zürich", "Basel", "Genève", "Bern");
                MeerkeuzeVraag m3 = new MeerkeuzeVraag(1, "Wat is de kleinste staat van de wereld?", "Monaco", "Andorra", "Liechtenstein", "Vaticaan");
                MeerkeuzeVraag m4 = new MeerkeuzeVraag(1, "Welk land heeft de grootste goudmijnen?", "Sovjet-Unie", "U.S.A.", "Australië", "Zuid Afrika");
                MeerkeuzeVraag m5 = new MeerkeuzeVraag(1, "Hoe heet de grootste stad van de wereld?", "Londen", "New York", "Moskou", "Tokio");
                MeerkeuzeVraag m6 = new MeerkeuzeVraag(1, "Hoe heet de hoofdstad van Australië?", "Melbourne", "Sydney", "Brisbane", "Canberra");
                MeerkeuzeVraag m7 = new MeerkeuzeVraag(1, "Tot welk land behoort Groenland?", "Zweden", "Noorwegen", "Finland", "Denemarken");
                MeerkeuzeVraag m8 = new MeerkeuzeVraag(1, "Hoe heet de hoofdstad van U.S.A.?", "New York", "Las Vegas", "Philadelphia", "Washington");
                MeerkeuzeVraag m9 = new MeerkeuzeVraag(1, "Welke stad ligt niet in Zwitserland?", "Lugano", "Bern", "Luzern", "Fussen");

        Quiz quiz4 = new Quiz("Quiz2", "Wetenschap en natuur");
                quiz4.Id = 2;
                MeerkeuzeVraag a1 = new MeerkeuzeVraag(2, "Waar staat RAM voor? ", "Right access memory", "real access memory", "ready acess memory", "random access memory");
                MeerkeuzeVraag a2 = new MeerkeuzeVraag(2, "Wat betekent WIFI?", "Wireless Friendly", "Wireless firewall", "wireless fraud", "wireless fidelity");
                MeerkeuzeVraag a3 = new MeerkeuzeVraag(2,"Wat wordt blauw onder basische omstandigheden en rood onder zure omstandigheden?", "Briefpapier", "overtrekpapier", "schuurpapier", "lakmoespapier");
                MeerkeuzeVraag a4 = new MeerkeuzeVraag(2,"Wat is het scheikundige symbool voor het element hassium?", "Ht", "Db", "Bh", "Hs");
                MeerkeuzeVraag a5 = new MeerkeuzeVraag(2, "Hoe heet de grootste stad van de wereld?", "Londen", "New York", "Moskou", "Tokio");
                MeerkeuzeVraag a6 = new MeerkeuzeVraag(2, "Wie lukte het als eerste om radiosignalen te verzenden over de Atlantische Oceaan?", "Bell", "Fleming", "Einstein", "Marconi");

                Score score1 = new Score(1, "thewinner", 4, "6");
                Score score2 = new Score(1, "nickname2", 3, "5");
                Score score3 = new Score(2, "nickname1", 2, "6");
                Score score4 = new Score(2, "nickname2", 1, "6");

                _dbContext.AddRange(quiz3,quiz4);
                _dbContext.AddRange(m1,m2,m3,m4,m5,m6,m7,m8,m9,a1,a2,a3,a4,a5,a6);
                _dbContext.AddRange(score1, score2, score3, score4);

                Speler speler = new Speler { Email = "jordivanderpoten@hotmail.com", NickName = "Jordi530"};
                _dbContext.Spelers.Add(speler);
                await CreateUser(speler.Email,"web4", "gelukkiggeennetbeans");
                _dbContext.SaveChanges();
            }
        }
        private async Task CreateUser(string email,string nicknaam, string password)
        {
            var user = new IdentityUser { UserName = nicknaam, Email = email };
            await _userManager.CreateAsync(user, password);
        }
    }
}
