using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace QuizApi.DTOs
{
    public class RegisterDTO : LoginDTO
    {
    [Required]
    [EmailAddress]
    public string Email { get; set; }

    [Required]
    [StringLength(30, MinimumLength = 8, ErrorMessage = "Wachtwoord moet langer zijn dan 7 karakters")]
    public string Password { get; set; }
  }
}
