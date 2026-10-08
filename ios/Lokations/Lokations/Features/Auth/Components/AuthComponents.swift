import SwiftUI

enum AuthField: Hashable {
    case email, password

    var placeholder: String { self == .email ? "Email" : "Mot de passe" }
    var icon: String { self == .email ? "envelope" : "lock" }
}

struct AuthBackground: View {
    let imageName: String

    var body: some View {
        ZStack {
            Image(imageName).resizable().scaledToFill().ignoresSafeArea()
            LinearGradient(colors: [.clear, DS.scrim(0.85)], startPoint: .top, endPoint: .bottom)
                .ignoresSafeArea()
        }
        .animation(.easeInOut, value: imageName)
    }
}

struct AuthTextField: View {
    let field: AuthField
    @Binding var text: String
    var focused: FocusState<AuthField?>.Binding

    var body: some View {
        HStack(spacing: DS.Space.sm) {
            Image(systemName: field.icon)
                .foregroundStyle(DS.onDarkAlpha(0.8))
            input
                .focused(focused, equals: field)
                .foregroundStyle(DS.onDark)
        }
        .padding(DS.Space.md)
        .background(.ultraThinMaterial, in: RoundedRectangle(cornerRadius: DS.Radius.md))
        .environment(\.colorScheme, .dark)
    }

    @ViewBuilder
    private var input: some View {
        let prompt = Text(field.placeholder).foregroundColor(DS.onDarkAlpha(0.6))
        switch field {
        case .email:
            TextField("", text: $text, prompt: prompt)
                .keyboardType(.emailAddress)
                .textContentType(.emailAddress)
                .textInputAutocapitalization(.never)
                .autocorrectionDisabled()
                .submitLabel(.next)
        case .password:
            SecureField("", text: $text, prompt: prompt)
                .textContentType(.password)
                .submitLabel(.go)
        }
    }
}

struct AuthPrimaryButton: View {
    let title: String
    let isLoading: Bool
    let action: () -> Void

    var body: some View {
        Button(action: action) {
            HStack(spacing: DS.Space.sm) {
                if isLoading { ProgressView().tint(DS.ink) }
                Text(title).fontWeight(.semibold)
            }
            .frame(maxWidth: .infinity)
            .padding(DS.Space.md)
            .background(DS.onDark, in: RoundedRectangle(cornerRadius: DS.Radius.md))
            .foregroundStyle(DS.ink)
        }
        .disabled(isLoading)
    }
}
